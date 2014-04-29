package com.blackboxsociety.net

import scalaz.{Reader => _, _}
import scalaz.syntax.bind._
import com.blackboxsociety.services._
import scalaz.concurrent._
import scalaz.concurrent.Task._
import scalaz.syntax.id._
import java.nio.channels._
import java.nio._
import java.nio.charset.Charset
import com.blackboxsociety.util._

trait TcpClient {
  def read(): Task[Finishable[ByteBuffer]]
  def readAsString(): Task[Finishable[String]]
  def write(b: Array[Byte]): Task[Unit]
  def write(s: String): Task[Unit]
  def write(c: FileChannel, o: Long = 0): Task[Unit]
  def end(b: Array[Byte]): Task[Unit]
  def end(s: String): Task[Unit]
  def end(c: FileChannel): Task[Unit]
  def close(): Task[Unit]
  def close(n: Unit): Task[Unit]
}

object TcpClient {

  def apply(socket: SocketChannel): TcpClient = {
    socket.configureBlocking(false)
    TcpClientImpl(socket)
  }

  private case class TcpClientImpl(s: SocketChannel) extends TcpClient {

    def read(): Task[Finishable[ByteBuffer]] = async { next =>
      EventLoop.addSocketRead(s, { () =>
        val buffer = ByteBuffer.allocate(8192)
        s.read(buffer) match {
          case -1 => next(Done(buffer).right)
          case _  => next(More(buffer).right)
        }
      })
    }

    def readAsString(): Task[Finishable[String]] = read map { n =>
      n map { b => new String(b.array(), Charset.forName("UTF-8")) }
    }

    def write(b: Array[Byte]): Task[Unit] = async { next =>
      EventLoop.addSocketWrite(s, { () =>
        val buffer = ByteBuffer.allocate(b.length)
        buffer.clear()
        buffer.put(b)
        buffer.flip()
        while(buffer.hasRemaining) {
          s.write(buffer)
        }
        next(\/-(Unit))
      })
    }

    def write(s: String): Task[Unit] = {
      write(s.getBytes)
    }

    def write(c: FileChannel, o: Long = 0): Task[Unit] = async { next =>
      EventLoop.addSocketWrite(s, { () =>
        val size = c.size()
        val sent = c.transferTo(o, size, s)
        val rem  = o + sent
        if (rem < c.size()) {
          write(c, rem).runAsync(next)
        } else {
          next(\/-(Unit))
        }
      })
    }

    def end(b: Array[Byte]): Task[Unit] = write(b) >>= close

    def end(s: String): Task[Unit] = write(s) >>= close

    def end(c: FileChannel): Task[Unit] = write(c) >>= close

    def close(): Task[Unit] = async { next =>
      EventLoop.closeChannel(s)
      s.close()
      next(\/-(Unit))
    }

    def close(n: Unit): Task[Unit] = close()

  }

}

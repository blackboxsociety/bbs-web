package com.blackboxsociety.net

import scalaz.{Reader => _, _}
import syntax.bind._
import com.blackboxsociety.services._
import scalaz.concurrent._
import scalaz.concurrent.Task._
import scalaz.syntax.id._
import java.nio.channels._
import java.nio._
import java.nio.charset.Charset

trait TcpClient {
  def read(): Task[ByteBuffer]
  def readAsString(): Task[String]
  def write(b: Array[Byte]): Task[Unit]
  def write(s: String): Task[Unit]
  def end(s: String): Task[Unit]
  def close(): Task[Unit]
  def close(n: Unit): Task[Unit]
}

object TcpClient {

  def apply(socket: SocketChannel): TcpClient = {
    socket.configureBlocking(false)
    TcpClientImpl(socket)
  }

  private case class TcpClientImpl(s: SocketChannel) extends TcpClient {

    def read(): Task[ByteBuffer] = async { next =>
      EventLoop.addSocketRead(s, { () =>
        val buffer = ByteBuffer.allocate(8192)
        s.read(buffer)
        next(buffer.right)
      })
    }

    def readAsString(): Task[String] = read map { n =>
      new String(n.array(), Charset.forName("UTF-8"))
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

    def end(s: String): Task[Unit] = write(s) >>= close

    def close(): Task[Unit] = async { next =>
      EventLoop.closeChannel(s)
      s.close()
      next(\/-(Unit))
    }

    def close(n: Unit): Task[Unit] = close()

  }

}

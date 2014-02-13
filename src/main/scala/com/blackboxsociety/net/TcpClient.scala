package com.blackboxsociety.net

import scalaz.{Reader => _, _}
import syntax.bind._
import com.blackboxsociety.services._
import scalaz.concurrent._
import Future._
import java.nio.channels._
import java.nio._
import java.nio.charset.Charset

trait TcpClient {
  def read(): Future[ByteBuffer]
  def readAsString(): Future[String]
  def write(b: Array[Byte]): Future[Unit]
  def write(s: String): Future[Unit]
  def end(s: String): Future[Unit]
  def close(): Future[Unit]
  def close(n: Unit): Future[Unit]
}

object TcpClient {

  def apply(socket: SocketChannel): TcpClient = {
    socket.configureBlocking(false)
    TcpClientImpl(socket)
  }

  private case class TcpClientImpl(s: SocketChannel) extends TcpClient {

    def read(): Future[ByteBuffer] = async { next =>
      EventLoop.addSocketRead(s, { () =>
        val buffer = ByteBuffer.allocate(8192)
        s.read(buffer)
        next(buffer)
      })
    }

    def readAsString(): Future[String] = read map { n =>
      new String(n.array(), Charset.forName("UTF-8"))
    }

    def write(b: Array[Byte]): Future[Unit] = async { next =>
      EventLoop.addSocketWrite(s, { () =>
        val buffer = ByteBuffer.allocate(b.length)
        buffer.clear()
        buffer.put(b)
        buffer.flip()
        while(buffer.hasRemaining) {
          s.write(buffer)
        }
        next()
      })
    }

    def write(s: String): Future[Unit] = {
      write(s.getBytes)
    }

    def end(s: String): Future[Unit] = write(s) >>= close

    def close(): Future[Unit] = async { next =>
      EventLoop.closeChannel(s)
      s.close()
      next()
    }

    def close(n: Unit): Future[Unit] = close()

  }

}

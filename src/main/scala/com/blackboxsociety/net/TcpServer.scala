package com.blackboxsociety.net

import java.net._
import scalaz.concurrent._
import Future._
import java.nio.channels._
import com.blackboxsociety.services.EventLoop


trait TcpServer {
  def accept(): Future[TcpClient]
  def close(): Future[Unit]
}

object TcpServer {

  def apply(host: String, port: Int): Future[TcpServer] = now {
    val channel = ServerSocketChannel.open
    val address = new InetSocketAddress(port)
    channel.socket().bind(address)
    channel.configureBlocking(false)
    TcpServerImpl(channel)
  }

  private case class TcpServerImpl(s: ServerSocketChannel) extends TcpServer {

    def accept(): Future[TcpClient] = async { next =>
      EventLoop.addServerSocketAccept(s, { () =>
        val client = s.accept()
        next(TcpClient(client))
      })
    }

    def close(): Future[Unit] = async { next =>
      EventLoop.closeChannel(s)
      s.close()
      next()
    }

  }

}

package com.blackboxsociety.util.parser

import com.blackboxsociety.net._
import scalaz.concurrent._

trait ParserStream {
  val current: String
  def latest: Task[ParserStream]
  def withText(s: String): ParserStream
}

case class TcpParserStream(client: TcpClient, current: String = "") extends ParserStream {

  def latest: Task[ParserStream] =
    client.readAsString() map { s => TcpParserStream(client, current + s) }

  def withText(s: String): ParserStream =
    TcpParserStream(client, s)

}

object TcpParserStream {

  implicit def TcpClientToParserStream(client: TcpClient): TcpParserStream = TcpParserStream(client)

}
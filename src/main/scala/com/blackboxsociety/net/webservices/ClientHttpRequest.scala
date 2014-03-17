package com.blackboxsociety.net.webservices

import scalaz.concurrent.Task
import com.blackboxsociety.http._
import java.net.InetSocketAddress
import java.nio.channels.SocketChannel
import com.blackboxsociety.net.TcpClient
import com.blackboxsociety.util.parser.TcpParserStream._

trait HttpMethods {

  def delete(resource: String): Task[ClientHttpResponse]
  def get(resource: String, params: (String, String)*): Task[ClientHttpResponse]
  def head(resource: String): Task[ClientHttpResponse]
  def options(resource: String): Task[ClientHttpResponse]

  def post(resource: String, params: (String, String)*): Task[ClientHttpResponse]
  def post[A](resource: String, body: A): Task[ClientHttpResponse]

  def put(resource: String, params: (String, String)*): Task[ClientHttpResponse]
  def put[A](resource: String, body: A): Task[ClientHttpResponse]

}

case class ClientHttpRequest(url: String,
                             headers: Seq[HttpHeader],
                             version: HttpVersion = HttpVersionOneDotOne) extends HttpMethods {

  def withHttpVersion(v: HttpVersion): ClientHttpRequest = {
    this.copy(version = v)
  }

  // fix auth type hashing
  def withAuthentication(username: String, password: String, authType: AuthType): ClientHttpRequest = {
    this.copy(headers = this.headers.:+(AuthorizationHeader(authType(username, password))))
  }

  def withHeaders(hs: HttpHeader*): ClientHttpRequest = {
    this.copy(headers = this.headers.++(hs))
  }

  def delete(resource: String): Task[ClientHttpResponse] = {
    val req = HttpDelete + s" $resource $version\r\n"
    headers.map{h => (s"$h.key: $h.value\r\n")}.mkString

    query(req)
  }

  def get(resource: String, params: (String, String)*): Task[ClientHttpResponse] = {
    val req = HttpGet + s" $resource?" + params.map{p => (s"$p._1=$p._2")}.mkString("&") + s" $version\r\n"
              headers.map{h => (s"$h.key: $h.value\r\n")}.mkString

    query(req)
  }

  def head(resource: String): Task[ClientHttpResponse] = {
    val req = HttpHead + s" $resource $version\r\n"
    headers.map{h => (s"$h.key: $h.value\r\n")}.mkString

    query(req)
  }

  def options(resource: String): Task[ClientHttpResponse] = {
    val req = HttpOptions + s" $resource $version\r\n"
    headers.map{h => (s"$h.key: $h.value\r\n")}.mkString

    query(req)
  }

  def post(resource: String, params: (String, String)*): Task[ClientHttpResponse] = {
    post(resource, params.map{p => (s"$p._1=$p._2")}.mkString("&"))
  }

  def post[A](resource: String, body: A): Task[ClientHttpResponse] = {
    val req = HttpPost + s" $resource $version\r\n" + headers.map{h => (s"$h.key: $h.value\r\n")}.mkString + "\r\n" +
      body.toString

    query(req)
  }

  def put(resource: String, params: (String, String)*): Task[ClientHttpResponse] = {
    put(resource, params.map{p => (s"$p._1=$p._2")}.mkString("&"))
  }

  def put[A](resource: String, body: A): Task[ClientHttpResponse] = {
    val req = HttpPut + s" $resource $version\r\n" + headers.map{h => (s"$h.key: $h.value\r\n")}.mkString + "\r\n" +
      body.toString

    query(req)
  }

  private def query(req: String): Task[ClientHttpResponse] = {
    val c = TcpClient(SocketChannel.open(new InetSocketAddress(url, 80)))

    for {
      w <- c.write(req);
      r <- HttpResponseParser(c)
    } yield r
  }

}




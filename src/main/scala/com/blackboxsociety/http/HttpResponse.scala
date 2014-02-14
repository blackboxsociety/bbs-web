package com.blackboxsociety.http

trait HttpResponse {

  val statusCode: Int
  val body:       String
  val headers:    List[String]

  def make(s: String, l: List[String]): HttpResponse

  def withHeader(header: String): HttpResponse = make(body, headers :+ header)

  def withHeaders(newHeaders: List[String]): HttpResponse = make(body, headers ++ newHeaders)

  override def toString: String = {
    s"HTTP/1.1 $statusCode OK\r\n" + headers.mkString("\r\n") + "\r\n" + body
  }

}
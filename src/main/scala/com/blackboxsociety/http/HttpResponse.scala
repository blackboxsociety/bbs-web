package com.blackboxsociety.http

trait HttpResponse {

  type Self <: HttpResponse

  val statusCode: Int
  val body:       String
  val headers:    List[String]

  def withHeader(header: String): Self

  def withHeaders(newHeaders: List[String]): Self

  override def toString: String = {
    s"HTTP/1.1 $statusCode OK\r\n" + headers.mkString("\r\n") + "\r\n" + body
  }

}

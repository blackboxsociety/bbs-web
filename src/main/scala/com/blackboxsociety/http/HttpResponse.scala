package com.blackboxsociety.http

trait HttpResponse {

  val statusCode: Int
  val body:       String
  val headers:    List[String]
  val session:    Map[String, String]

  def make(c: String, l: List[String], s: Map[String, String]): HttpResponse

  def withHeader(header: String): HttpResponse = make(body, headers :+ header, session)

  def withHeaders(newHeaders: List[String]): HttpResponse = make(body, headers ++ newHeaders, session)

  def withSession(s: Map[String, String]): HttpResponse = make(body, headers, s)

  def withNewSession: HttpResponse = make(body, headers, Map())

  override def toString: String = {
    s"HTTP/1.1 $statusCode OK\r\n" + headers.mkString("\r\n") + "\r\n" + body
  }

}
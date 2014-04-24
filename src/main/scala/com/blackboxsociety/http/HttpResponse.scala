package com.blackboxsociety.http

abstract class HttpResponse {

  val statusCode: Int
  val body:       String
  val headers:    List[HttpHeader]
  val session:    Option[SignedSession]

  def make(c: String, l: List[HttpHeader], s: Option[SignedSession]): HttpResponse

  def withHeader(header: HttpHeader): HttpResponse = make(body, headers :+ header, session)

  def withHeaders(newHeaders: List[HttpHeader]): HttpResponse = make(body, headers ++ newHeaders, session)

  def withSession(s: SignedSession): HttpResponse = make(body, headers, Some(s))

}
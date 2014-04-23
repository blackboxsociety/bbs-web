package com.blackboxsociety.http

import play.api.libs.json.Json
import com.blackboxsociety.security.crypto.{SignedMap, Signed}

abstract class HttpResponse {

  val statusCode: Int
  val body:       String
  val headers:    List[HttpHeader]
  val session:    Option[SignedSession]

  def make(c: String, l: List[HttpHeader], s: Option[SignedSession]): HttpResponse

  def withHeader(header: HttpHeader): HttpResponse = make(body, headers :+ header, session)

  def withHeaders(newHeaders: List[HttpHeader]): HttpResponse = make(body, headers ++ newHeaders, session)

  def withSession(s: SignedSession): HttpResponse = make(body, headers, Some(s))

  override def toString: String = {
    if (session.nonEmpty && session.get.data.size > 0) { // bad code will be refactored in issue #37
      val json   = Json.toJson(session.get.data)
      val signed = session.get.signature()
      withSession(session.get.clear()).withHeader(SetCookieHeader(s"session=$signed$json; HttpOnly")).toString
    } else {
      if (headers.size > 0) {
        val serializedHeaders = headers.map(_.toString).mkString("\r\n")
        s"HTTP/1.1 $statusCode OK\r\n$serializedHeaders\r\n\r\n$body"
      } else {
        s"HTTP/1.1 $statusCode OK\r\n\r\n" + body
      }
    }
  }

}
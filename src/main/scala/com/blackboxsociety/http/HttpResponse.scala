package com.blackboxsociety.http

import com.blackboxsociety.app.services.ServiceManifest
import play.api.libs.json.Json
import com.blackboxsociety.security.crypto.Signed

abstract class HttpResponse(implicit services: ServiceManifest) {

  val statusCode: Int
  val body:       String
  val headers:    List[HttpHeader]
  val session:    Map[String, String]

  def make(c: String, l: List[HttpHeader], s: Map[String, String]): HttpResponse

  def withHeader(header: HttpHeader): HttpResponse = make(body, headers :+ header, session)

  def withHeaders(newHeaders: List[HttpHeader]): HttpResponse = make(body, headers ++ newHeaders, session)

  def withSession(s: Map[String, String]): HttpResponse = make(body, headers, s)

  def withSession(s: (String, String)*): HttpResponse = make(body, headers, s.groupBy(_._1).mapValues(_.head._2))

  def withNewSession: HttpResponse = make(body, headers, Map())

  override def toString: String = {
    if (session.size > 0) {
      val json   = Json.toJson(session)
      val signed = Signed.sign(services.sessionSecret, json.toString())
      withNewSession.withHeader(SetCookieHeader(s"session=$signed; HttpOnly")).toString
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
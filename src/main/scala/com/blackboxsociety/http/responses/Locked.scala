package com.blackboxsociety.http.responses

import com.blackboxsociety.http.{HttpHeader, HttpResponse}
import com.blackboxsociety.app.services.ServiceManifest

case class Locked(body: String,
                  headers: List[HttpHeader] = List(),
                  session: Map[String, String] = Map())
                 (implicit services: ServiceManifest) extends HttpResponse
{
  val statusCode: Int = 423
  def make(body: String, headers: List[HttpHeader], session: Map[String, String]) =
    Locked(body, headers, session)
}

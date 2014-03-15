package com.blackboxsociety.http.responses

import com.blackboxsociety.http.{HttpHeader, HttpResponse}
import com.blackboxsociety.app.services.ServiceManifest

case class Accepted(body: String,
                    headers: List[HttpHeader] = List(),
                    session: Map[String, String] = Map())
                   (implicit services: ServiceManifest) extends HttpResponse
{
  val statusCode: Int = 202
  def make(body: String, headers: List[HttpHeader], session: Map[String, String]) =
    Accepted(body, headers, session)
}

package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse
import com.blackboxsociety.app.services.ServiceManifest

case class MethodNotAllowed(body: String,
                            headers: List[String] = List(),
                            session: Map[String, String] = Map())
                           (implicit services: ServiceManifest) extends HttpResponse
{
  val statusCode: Int = 405
  def make(body: String, headers: List[String], session: Map[String, String]) =
    MethodNotAllowed(body, headers, session)
}
package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse
import com.blackboxsociety.app.services.ServiceManifest

case class Conflict(body: String,
                    headers: List[String] = List(),
                    session: Map[String, String] = Map())
                   (implicit services: ServiceManifest) extends HttpResponse
{
  val statusCode: Int = 409
  def make(body: String, headers: List[String], session: Map[String, String]) =
    Conflict(body, headers, session)
}
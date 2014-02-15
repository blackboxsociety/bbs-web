package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse
import com.blackboxsociety.app.services.ServiceManifest

case class TemporaryRedirect(body: String,
                             headers: List[String] = List(),
                             session: Map[String, String] = Map())
                            (implicit services: ServiceManifest) extends HttpResponse
{
  val statusCode: Int = 307
  def make(body: String, headers: List[String], session: Map[String, String]) =
    TemporaryRedirect(body, headers, session)
}
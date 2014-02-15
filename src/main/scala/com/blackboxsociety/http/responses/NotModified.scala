package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse
import com.blackboxsociety.app.services.ServiceManifest

case class NotModified(body: String,
                       headers: List[String] = List(),
                       session: Map[String, String] = Map())
                      (implicit services: ServiceManifest) extends HttpResponse
{
  val statusCode: Int = 304
  def make(body: String, headers: List[String], session: Map[String, String]) =
    NotModified(body, headers, session)
}
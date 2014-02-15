package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse
import com.blackboxsociety.app.services.ServiceManifest

case class NotImplemented(body: String,
                          headers: List[String] = List(),
                          session: Map[String, String] = Map())
                         (implicit services: ServiceManifest) extends HttpResponse
{
  val statusCode: Int = 501
  def make(body: String, headers: List[String], session: Map[String, String]) =
    NotImplemented(body, headers, session)
}
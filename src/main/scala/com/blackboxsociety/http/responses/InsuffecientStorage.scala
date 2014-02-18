package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse
import com.blackboxsociety.app.services.ServiceManifest

case class InsuffecientStorage(body: String,
                               headers: List[String] = List(),
                               session: Map[String, String] = Map())
                              (implicit services: ServiceManifest)extends HttpResponse
{
  val statusCode: Int = 507
  def make(body: String, headers: List[String], session: Map[String, String]) =
    InsuffecientStorage(body, headers, session)
}
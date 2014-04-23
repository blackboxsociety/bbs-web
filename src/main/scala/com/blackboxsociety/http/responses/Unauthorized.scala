package com.blackboxsociety.http.responses

import com.blackboxsociety.http.{SignedSession, HttpHeader, HttpResponse}
import com.blackboxsociety.app.services.ServiceManifest

case class Unauthorized(body: String,
                        headers: List[HttpHeader] = List(),
                        session: Option[SignedSession] = None) extends HttpResponse
{
  val statusCode: Int = 401
  def make(body: String, headers: List[HttpHeader], session: Option[SignedSession]) =
    Unauthorized(body, headers, session)
}

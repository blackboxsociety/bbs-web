package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class PreconditionFailed(body: String,
                              headers: List[String] = List(),
                              session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 412
  def make(body: String, headers: List[String], session: Map[String, String]) =
    PreconditionFailed(body, headers, session)
}
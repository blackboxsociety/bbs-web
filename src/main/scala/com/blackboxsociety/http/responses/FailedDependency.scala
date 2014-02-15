package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class FailedDependency(body: String,
                            headers: List[String] = List(),
                            session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 424
  def make(body: String, headers: List[String], session: Map[String, String]) =
    FailedDependency(body, headers, session)
}
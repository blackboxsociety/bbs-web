package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class NoContent(body: String,
                     headers: List[String] = List(),
                     session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 204
  def make(body: String, headers: List[String], session: Map[String, String]) =
    NoContent(body, headers, session)
}
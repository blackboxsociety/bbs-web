package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Missing(body: String,
                   headers: List[String] = List(),
                   session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 404
  def make(body: String, headers: List[String], session: Map[String, String]) =
    Missing(body, headers, session)
}
package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Found(body: String,
                 headers: List[String] = List(),
                 session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 302
  def make(body: String, headers: List[String], session: Map[String, String]) =
    Found(body, headers, session)
}
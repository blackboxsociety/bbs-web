package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class TooManyRequests(body: String,
                           headers: List[String] = List(),
                           session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 429
  def make(body: String, headers: List[String], session: Map[String, String]) =
    TooManyRequests(body, headers, session)
}
package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Forbidden(body: String,
                     headers: List[String] = List(),
                     session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 403
  def make(body: String, headers: List[String], session: Map[String, String]) =
    Forbidden(body, headers, session)
}
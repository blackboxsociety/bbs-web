package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Accepted(body: String,
                    headers: List[String] = List(),
                    session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 202
  def make(body: String, headers: List[String], session: Map[String, String]) =
    Accepted(body, headers, session)
}
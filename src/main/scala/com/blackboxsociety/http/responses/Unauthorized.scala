package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Unauthorized(body: String,
                        headers: List[String] = List(),
                        session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 401
  def make(body: String, headers: List[String], session: Map[String, String]) =
    Unauthorized(body, headers, session)
}
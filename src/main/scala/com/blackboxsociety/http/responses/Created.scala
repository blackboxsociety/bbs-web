package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Created(body: String,
                   headers: List[String] = List(),
                   session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 201
  def make(body: String, headers: List[String], session: Map[String, String]) =
    Created(body, headers, session)
}
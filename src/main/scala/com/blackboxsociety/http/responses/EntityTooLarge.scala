package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class EntityTooLarge(body: String,
                          headers: List[String] = List(),
                          session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 413
  def make(body: String, headers: List[String], session: Map[String, String]) =
    EntityTooLarge(body, headers, session)
}
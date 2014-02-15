package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class GatewayTimeout(body: String,
                          headers: List[String] = List(),
                          session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 504
  def make(body: String, headers: List[String], session: Map[String, String]) =
    GatewayTimeout(body, headers, session)
}
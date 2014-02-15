package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class PartialContent(body: String,
                          headers: List[String] = List(),
                          session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 206
  def make(body: String, headers: List[String], session: Map[String, String]) =
    PartialContent(body, headers, session)
}
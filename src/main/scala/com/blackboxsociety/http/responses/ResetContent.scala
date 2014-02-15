package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class ResetContent(body: String,
                        headers: List[String] = List(),
                        session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 205
  def make(body: String, headers: List[String], session: Map[String, String]) =
    ResetContent(body, headers, session)
}
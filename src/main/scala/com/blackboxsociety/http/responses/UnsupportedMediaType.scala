package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class UnsupportedMediaType(body: String,
                                headers: List[String] = List(),
                                session: Map[String, String] = Map()) extends HttpResponse
{
  val statusCode: Int = 415
  def make(body: String, headers: List[String], session: Map[String, String]) =
    UnsupportedMediaType(body, headers, session)
}
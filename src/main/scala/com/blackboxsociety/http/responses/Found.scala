package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Found(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 302
  def make(body: String, headers: List[String]) = Found(body, headers)
}
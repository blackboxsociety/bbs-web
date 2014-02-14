package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class NoContent(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 204
  def make(body: String, headers: List[String]) = NoContent(body, headers)
}
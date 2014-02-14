package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Conflict(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 409
  def make(body: String, headers: List[String]) = Conflict(body, headers)
}
package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Unauthorized(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 401
  def make(body: String, headers: List[String]) = Unauthorized(body, headers)
}
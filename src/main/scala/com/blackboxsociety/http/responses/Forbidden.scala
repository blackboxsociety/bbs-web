package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Forbidden(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 403
  def make(body: String, headers: List[String]) = Forbidden(body, headers)
}
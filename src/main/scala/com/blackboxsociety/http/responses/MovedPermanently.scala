package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class MovedPermanently(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 301
  def make(body: String, headers: List[String]) = MovedPermanently(body, headers)
}
package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class UriTooLong(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 414
  def make(body: String, headers: List[String]) = UriTooLong(body, headers)
}
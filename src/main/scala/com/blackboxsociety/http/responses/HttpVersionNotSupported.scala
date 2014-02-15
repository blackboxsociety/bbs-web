package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class HttpVersionNotSupported(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 505
  def make(body: String, headers: List[String]) = HttpVersionNotSupported(body, headers)
}
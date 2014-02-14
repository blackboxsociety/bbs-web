package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class NotModified(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 304
  def make(body: String, headers: List[String]) = NotModified(body, headers)
}
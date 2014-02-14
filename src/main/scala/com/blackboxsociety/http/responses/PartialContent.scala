package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class PartialContent(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 206
  def make(body: String, headers: List[String]) = PartialContent(body, headers)
}
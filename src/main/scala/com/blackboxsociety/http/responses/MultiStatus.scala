package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class MultiStatus(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 207
  def make(body: String, headers: List[String]) = MultiStatus(body, headers)
}
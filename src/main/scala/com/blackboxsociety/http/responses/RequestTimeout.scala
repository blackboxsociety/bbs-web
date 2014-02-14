package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class RequestTimeout(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 408
  def make(body: String, headers: List[String]) = RequestTimeout(body, headers)
}
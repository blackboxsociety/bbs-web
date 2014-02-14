package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class ExpectationFailed(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 417
  def make(body: String, headers: List[String]) = ExpectationFailed(body, headers)
}
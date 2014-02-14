package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Accepted(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 202
  def make(body: String, headers: List[String]) = Accepted(body, headers)
}
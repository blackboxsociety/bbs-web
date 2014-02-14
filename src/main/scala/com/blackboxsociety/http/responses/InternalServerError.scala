package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class InternalServerError(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 500
  def make(body: String, headers: List[String]) = InternalServerError(body, headers)
}
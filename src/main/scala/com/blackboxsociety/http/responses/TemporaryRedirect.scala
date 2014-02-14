package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class TemporaryRedirect(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 307
  def make(body: String, headers: List[String]) = TemporaryRedirect(body, headers)
}
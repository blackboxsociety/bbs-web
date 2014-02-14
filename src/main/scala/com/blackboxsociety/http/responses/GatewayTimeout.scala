package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class GatewayTimeout(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 504
  def make(body: String, headers: List[String]) = GatewayTimeout(body, headers)
}
package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Gone(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 410
  def make(body: String, headers: List[String]) = Gone(body, headers)
}
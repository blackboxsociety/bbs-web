package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Ok(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 200
  def make(body: String, headers: List[String]) = Ok(body, headers)
}
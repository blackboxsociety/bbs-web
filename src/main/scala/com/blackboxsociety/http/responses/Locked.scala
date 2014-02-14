package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class Locked(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 423
  def make(body: String, headers: List[String]) = Locked(body, headers)
}
package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class EntityTooLarge(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 413
  def make(body: String, headers: List[String]) = EntityTooLarge(body, headers)
}
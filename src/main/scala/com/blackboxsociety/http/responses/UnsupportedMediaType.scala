package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class UnsupportedMediaType(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 415
  def make(body: String, headers: List[String]) = UnsupportedMediaType(body, headers)
}
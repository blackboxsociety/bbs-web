package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class InsuffecientStorage(body: String, headers: List[String] = List()) extends HttpResponse {
  val statusCode: Int = 507
  def make(body: String, headers: List[String]) = InsuffecientStorage(body, headers)
}
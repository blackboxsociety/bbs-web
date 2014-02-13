package com.blackboxsociety.http.responses

import com.blackboxsociety.http.HttpResponse

case class OkResponse(body: String, headers: List[String] = List()) extends HttpResponse {

  type Self = OkResponse

  val statusCode: Int = 200

  def withHeader(header: String): Self = OkResponse(body, headers :+ header)

  def withHeaders(newHeaders: List[String]): Self = OkResponse(body, headers ++ newHeaders)

  override def toString: String = {
    s"HTTP/1.1 $statusCode OK\r\n" + headers.mkString("\r\n") + "\r\n" + body
  }

}
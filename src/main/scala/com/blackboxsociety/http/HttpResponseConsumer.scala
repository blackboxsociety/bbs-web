package com.blackboxsociety.http

object HttpResponseConsumer {

  def consume(response: HttpResponse): String = {
    val statusLine        = genStatusLine(response)
    val headerLines       = genHeaderLines(response)
    val sessionLines      = genSessionHeaderLines(response).getOrElse("")
    val contentLengthLine = genContentLengthLine(response)
    val bodyClause        = genBody(response)

    s"$statusLine$headerLines$sessionLines$contentLengthLine$bodyClause"
  }

  private def genStatusLine(response: HttpResponse): String = {
    s"HTTP/1.1 ${response.statusCode} OK\r\n"
  }

  private def genHeaderLines(response: HttpResponse): String = {
    if (response.headers.size > 0) {
      response.headers.map(_.toString).mkString("\r\n") + "\r\n"
    } else {
      ""
    }
  }

  private def genSessionHeaderLines(response: HttpResponse): Option[String] = {
    response.session map { s =>
      SetCookieHeader(s"session=${s.signature()}${s.toJson}; HttpOnly").toString + "\r\n"
    }
  }

  private def genContentLengthLine(response: HttpResponse): String = {
    ContentLengthHeader(response.body.length.toString).toString + "\r\n"
  }

  private def genBody(response: HttpResponse): String = {
    s"\r\n${response.body}"
  }

}

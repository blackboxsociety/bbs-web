package com.blackboxsociety.util.parser

import play.api.libs.json.{Json, JsValue}
import com.blackboxsociety.http.HttpRequest
import scalaz.concurrent.Task

object JsonBodyParser extends BodyParser[JsValue] {

  def fromBody(request: HttpRequest): Task[JsValue] = {
    request.getBody().map { s =>
      Json.parse(s)
    }
  }

}

package com.blackboxsociety.app.middleware.global

import scalaz.concurrent.Task
import com.blackboxsociety.http._

object RequestLoggerMiddleware {

  def apply()(next: HttpRequest => Task[HttpResponse])(request: HttpRequest): Task[HttpResponse] = {
    next(request) map { response =>
      println(s"${request.method} ${request.resource.path} -> ${response.statusCode}")
      response
    }
  }

}

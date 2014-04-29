package com.blackboxsociety.app.middleware.global

import scalaz.concurrent.Task
import com.blackboxsociety.http._

object RequestLoggerMiddleware {

  def apply()(next: HttpRequest => Task[HttpResponse]): HttpRequest => Task[HttpResponse] = { (request) =>
    next(request) map { response =>
      println(s"${request.method} ${request.resource.path} -> ${response.statusCode}")
      response
    }
  }

}

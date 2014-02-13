package com.blackboxsociety.http

import com.blackboxsociety.mvc._
import scalaz.concurrent._

case class HttpRouter(default: Controller, controllers: List[Controller]) {

  def route(request: HttpRequest): Future[HttpResponse] = {
    val found      = controllers.find(_.route.route(request))
    val controller = found.getOrElse(default)
    controller.run(request)
  }

}
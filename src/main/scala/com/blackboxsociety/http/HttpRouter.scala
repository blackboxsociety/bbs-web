package com.blackboxsociety.http

import com.blackboxsociety.mvc._
import scalaz.concurrent._
import scalaz.concurrent.Task._

case class MissingRouteException(r: HttpRequest) extends Throwable

case class HttpRouter(controllers: Controller*) {

  def route(request: HttpRequest): Task[HttpResponse] = {
    controllers.find(_.route.route(request)) match {
      case None    => fail(MissingRouteException(request))
      case Some(c) => c.run(request)
    }
  }

}
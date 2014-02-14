package com.blackboxsociety.mvc

import com.blackboxsociety.http._
import com.blackboxsociety.http.routes._
import scalaz.concurrent._
import scalaz.concurrent.Future._

trait Controller {

  val route: HttpRouteRule

  def middleware: List[(HttpRequest => Future[HttpResponse]) => (HttpRequest => Future[HttpResponse])] = List()

  val run = middleware.reverse.foldLeft[HttpRequest => Future[HttpResponse]](action) { (m, n) =>
    n(m)
  }

  def action(request: HttpRequest): Future[HttpResponse]

}
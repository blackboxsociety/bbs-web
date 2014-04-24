package com.blackboxsociety.mvc

import com.blackboxsociety.http._
import com.blackboxsociety.http.routes._
import scalaz.concurrent._

trait Controller {

  val number = """(\d+)"""

  val route: HttpRouteRule

  def middleware: List[(HttpRequest => Task[HttpResponse]) => (HttpRequest => Task[HttpResponse])] = List()

  val run = middleware.reverse.foldLeft[HttpRequest => Task[HttpResponse]](action) { (m, n) =>
    n(m)
  }

  def action(request: HttpRequest): Task[HttpResponse]

}
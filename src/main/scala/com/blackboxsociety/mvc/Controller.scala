package com.blackboxsociety.mvc

import com.blackboxsociety.http._
import com.blackboxsociety.http.routes._
import scalaz.concurrent._
import scalaz.concurrent.Future._

trait Controller {

  val route: HttpRouteRule

  def preAction(request: Future[HttpRequest]): Future[HttpRequest] = request

  def action(request: HttpRequest): Future[HttpResponse]

  def postAction(response: Future[HttpResponse]): Future[HttpResponse] = response

  def run(request: HttpRequest): Future[HttpResponse] = for (
    a <- preAction(now { request });
    b <- action(a);
    c <- postAction(now { b })
  ) yield c

}
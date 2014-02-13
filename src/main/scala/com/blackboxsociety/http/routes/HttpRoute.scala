package com.blackboxsociety.http.routes

import com.blackboxsociety.http._

case class HttpRoute(rules: HttpRouteRule*) extends HttpRouteRule {

  def route(request: HttpRequest): Boolean =
    rules.forall(_.route(request))

}
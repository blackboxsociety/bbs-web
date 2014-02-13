package com.blackboxsociety.http.routes

import com.blackboxsociety.http._

case class MethodRoute(method: HttpMethod) extends HttpRouteRule {

  def route(request: HttpRequest): Boolean = request.method == method

}
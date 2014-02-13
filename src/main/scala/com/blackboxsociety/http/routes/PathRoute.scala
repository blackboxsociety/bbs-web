package com.blackboxsociety.http.routes

import com.blackboxsociety.http._

case class PathRoute(path: String) extends HttpRouteRule {

  def route(request: HttpRequest): Boolean = request.resource.path == path

}
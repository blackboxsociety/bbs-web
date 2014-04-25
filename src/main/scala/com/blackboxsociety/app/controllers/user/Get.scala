package com.blackboxsociety.app.controllers.user

import com.blackboxsociety.mvc._
import com.blackboxsociety.http.routes._
import com.blackboxsociety.http._
import scalaz.concurrent._
import scalaz.concurrent.Task._
import com.blackboxsociety.app.services._
import com.blackboxsociety.http.HttpRequest
import com.blackboxsociety.http.routes.MethodRoute
import com.blackboxsociety.http.routes.HttpRoute
import com.blackboxsociety.http.routes.RegexPathRoute
import com.blackboxsociety.http.Ok

case class Get(implicit services: ServiceManifest) extends Controller {

  val route = HttpRoute(MethodRoute(HttpGet), RegexPathRoute(s"/user/$number/", "uid"))

  def action(request: HttpRequest): Task[HttpResponse] = now {
    Ok(request.getPathVar("uid").getOrElse("uid not found"))
  }

}

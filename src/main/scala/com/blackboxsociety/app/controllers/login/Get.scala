package com.blackboxsociety.app.controllers.login

import com.blackboxsociety.mvc._
import com.blackboxsociety.http.routes._
import com.blackboxsociety.http._
import scalaz.concurrent._
import scalaz.concurrent.Future._
import com.blackboxsociety.http.responses._
import com.blackboxsociety.app.services._

case class Get(implicit services: ServiceManifest) extends Controller {

  val route = HttpRoute(MethodRoute(HttpGet), PathRoute("/login"))

  def action(request: HttpRequest): Future[HttpResponse] = now {
    Ok("Hello from login")
  }

}

package com.blackboxsociety.app.controllers.index

import com.blackboxsociety.mvc._
import com.blackboxsociety.http.routes._
import com.blackboxsociety.http._
import scalaz.concurrent._
import scalaz.concurrent.Task._
import com.blackboxsociety.http.responses._
import com.blackboxsociety.app.services._
import com.blackboxsociety.app.views._

case class Get(implicit services: ServiceManifest) extends Controller {

  val route = HttpRoute(MethodRoute(HttpGet), PathRoute("/"))

  def action(request: HttpRequest): Task[HttpResponse] = now {
    Ok(Home()).withSession("key" -> "value", "no" -> "srs")
  }

}

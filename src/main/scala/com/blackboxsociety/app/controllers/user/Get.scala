package com.blackboxsociety.app.controllers.user

import com.blackboxsociety.mvc._
import com.blackboxsociety.http.routes._
import com.blackboxsociety.http._
import scalaz.concurrent._
import scalaz.concurrent.Task._
import com.blackboxsociety.http.responses._
import com.blackboxsociety.app.services._
import com.blackboxsociety.app.views._
import scala.util.matching.Regex

case class Get(implicit services: ServiceManifest) extends Controller {

  val route = HttpRoute(MethodRoute(HttpGet), RegexPathRoute(s"/user/$number/", "uid"))

  def action(request: HttpRequest): Task[HttpResponse] = now {
    Ok(request.getPathVar("uid").getOrElse("uid not found"))
  }

}

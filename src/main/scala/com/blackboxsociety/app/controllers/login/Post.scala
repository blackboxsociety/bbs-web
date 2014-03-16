package com.blackboxsociety.app.controllers.login

import com.blackboxsociety.mvc._
import com.blackboxsociety.http.routes._
import com.blackboxsociety.http._
import scalaz.concurrent._
import scalaz.concurrent.Task._
import com.blackboxsociety.http.responses._
import com.blackboxsociety.app.services._

case class Post(implicit services: ServiceManifest) extends Controller {

  val route = HttpRoute(MethodRoute(HttpPost), PathRoute("/login"))

  def action(request: HttpRequest): Task[HttpResponse] = now {
    println(request)
    println(request.body.current)
    Ok("Hello from login post")
  }

}

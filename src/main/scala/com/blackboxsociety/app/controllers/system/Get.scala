package com.blackboxsociety.app.controllers.system

import com.blackboxsociety.mvc._
import com.blackboxsociety.http.routes._
import com.blackboxsociety.http._
import scalaz.concurrent._
import scalaz.concurrent.Future._
import com.blackboxsociety.http.responses._
import com.blackboxsociety.app.services._
import java.io.File

case class Get(services: ServiceManifest) extends Controller {

  val route = HttpRoute(MethodRoute(HttpGet), PathRoute("/"))

  def action(request: HttpRequest): Future[HttpResponse] = now {
    if(request.resource.path.startsWith("/assets/")) {
      val file = request.resource.path.substring(8)
      if(new File("target/scala-2.10/public/" + file).exists())
        Ok(scala.io.Source.fromFile("target/scala-2.10/public/" + file).mkString)
      else
        Ok("/assets/" + file + " doesn't exist.")
    } else {
      Ok(":-/ this isn't the droid you were looking for.")
    }
  }

}

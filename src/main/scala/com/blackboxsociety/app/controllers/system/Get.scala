package com.blackboxsociety.app.controllers.system

import com.blackboxsociety.mvc._
import com.blackboxsociety.http.routes._
import com.blackboxsociety.http._
import scalaz.concurrent._
import scalaz.concurrent.Task._
import com.blackboxsociety.http.responses._
import com.blackboxsociety.app.services._
import java.io.File

case class Get(implicit services: ServiceManifest) extends Controller {

  val route = HttpRoute(MethodRoute(HttpGet), PathRoute("/"))

  def action(request: HttpRequest): Task[HttpResponse] = now {
    if(request.resource.path.startsWith("/assets/")) {
      val src = "target/public/" + request.resource.path.substring(8)
      val file = new File(src)
      if(file.exists() && !src.contains("..")) {
        Ok(scala.io.Source.fromFile(file).mkString)
      } else {
        Ok("that resource doesn't exist.")
      }
    } else {
      Ok(":-/ this isn't the droid you were looking for.")
    }
  }

}

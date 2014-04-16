package com.blackboxsociety.app.controllers.login

import com.blackboxsociety.mvc._
import com.blackboxsociety.http.routes._
import com.blackboxsociety.http._
import scalaz.concurrent._
import scalaz.concurrent.Task._
import com.blackboxsociety.http.responses._
import com.blackboxsociety.app.services._
import com.blackboxsociety.mvc.form.{FormConstraint, Form}

case class Get(implicit services: ServiceManifest) extends Controller {

  val route = HttpRoute(MethodRoute(HttpGet), PathRoute("/login"))

  val usernameC = FormConstraint().restrict(s => s.length > 3, "too short").restrict(s => s == "master", "you are not master")
  val passwordC = FormConstraint().restrict(s => !s.contains("123"), "contains 123")

  def action(request: HttpRequest): Task[HttpResponse] = now {
    val f = Form.form("username" -> usernameC, "password" -> passwordC).fromQueryString(request)
    f.run (
      success => Ok("Login success!"),
      failure => Ok(failure.errors()(0).error)
    )
  }

}

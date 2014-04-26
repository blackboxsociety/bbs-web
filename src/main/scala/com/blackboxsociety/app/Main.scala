package com.blackboxsociety.app

import com.blackboxsociety.services._
import com.blackboxsociety.app.services.DevServices
import com.blackboxsociety.app.middleware.global._

object Main extends BlackBox {

  override def middleware = List(
    RequestLoggerMiddleware.apply _
  )

  override def port: Int = 3000

  override def host: String = "0.0.0.0"

  override def router = DevServices.router

}

package com.blackboxsociety.app

import com.blackboxsociety.services._
import scalaz.ImmutableArray
import com.blackboxsociety.app.services.DevServices
import com.blackboxsociety.app.middleware.global._

object Main extends BlackBox {

  override val middleware = List(
    RequestLoggerMiddleware.apply _
  )

  final def main(args: Array[String]) {
    run(ImmutableArray.fromArray(args))
  }

  override val port: Int = 3000
  override val host: String = "0.0.0.0"
  override def router = DevServices.router

}

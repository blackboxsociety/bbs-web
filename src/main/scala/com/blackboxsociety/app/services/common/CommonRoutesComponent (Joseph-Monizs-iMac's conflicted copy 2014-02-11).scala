package com.blackboxsociety.app.services.common

import com.blackboxsociety.app.services.interfaces._
import com.blackboxsociety.http._
import com.blackboxsociety.app.services._

trait CommonRoutesComponent extends RoutesComponent { self: ServiceManifest =>

  val router = HttpRouter(
    default     = com.blackboxsociety.app.controllers.system.Get(this),
    controllers = List(
      com.blackboxsociety.app.controllers.index.Get(this),
      com.blackboxsociety.app.controllers.login.Get(this)
    )
  )

}
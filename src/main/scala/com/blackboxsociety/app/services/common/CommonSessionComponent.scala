package com.blackboxsociety.app.services.common

import com.blackboxsociety.app.services.interfaces._

trait CommonSessionComponent extends SessionComponent {

  val sessionSecret = "Public secret ain't much of a secret lulz"

}
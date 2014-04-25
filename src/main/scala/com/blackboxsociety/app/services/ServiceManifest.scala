package com.blackboxsociety.app.services

import com.blackboxsociety.app.services.interfaces.{SessionComponent, RoutesComponent}

trait   ServiceManifest
extends RoutesComponent
with    SessionComponent

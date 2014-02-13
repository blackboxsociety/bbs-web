package com.blackboxsociety.http

sealed trait HttpVersion
case object HttpVersionOneDotZero extends HttpVersion
case object HttpVersionOneDotOne  extends HttpVersion
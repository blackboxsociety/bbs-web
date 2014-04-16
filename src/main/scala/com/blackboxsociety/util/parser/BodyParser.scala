package com.blackboxsociety.util.parser

import com.blackboxsociety.http.HttpRequest
import scalaz.concurrent.Task

trait BodyParser[A] {

  def fromBody(request: HttpRequest): Task[A]

}

trait QueryStringParser[A] {

  def fromQueryString(request: HttpRequest): A

}




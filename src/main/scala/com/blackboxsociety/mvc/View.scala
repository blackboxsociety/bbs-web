package com.blackboxsociety.mvc

import scala.xml._
import scala.language.implicitConversions

trait View {

  def render: Elem

  override def toString: String = render.toString()

}

object View {

  implicit def renderableToString(r: View): String = r.toString

}
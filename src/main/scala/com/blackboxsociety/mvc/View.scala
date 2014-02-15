package com.blackboxsociety.mvc

import scalatags.HtmlTag
import scala.language.implicitConversions

trait View {

  def render: HtmlTag

  override def toString: String = render.toString()

}

object View {

  implicit def renderableToString(r: View): String = r.toString

}
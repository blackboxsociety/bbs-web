package com.blackboxsociety.mvc

import scalatags.HtmlTag
import scala.language.implicitConversions
import scala.xml.Elem

trait View[T] {

  def render: T

  override def toString: String = render.toString()

}

trait ScalaView extends View[HtmlTag] {

  def render: HtmlTag

}

trait HTMLView extends View[Elem] {

  def render: Elem

}

object View {

  implicit def renderableToString[T](r: View[T]): String = r.toString

}
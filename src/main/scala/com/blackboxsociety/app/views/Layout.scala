package com.blackboxsociety.app.views

import com.blackboxsociety.mvc._
import scalatags.{Tags2, HtmlTag}
import scalatags.all._

trait Layout extends View {

  def render: HtmlTag = html(
    head(
      Tags2.title("Black Box Society"),
      meta(charset := "utf-8"),
      meta(name := "viewport", content := "width=device-width, initial-scale=1.0"),
      meta(name := "description", content := "Spreading and connecting information")
    ),
    body(
      div(`class`:="top nav")(
        div(`class`:="brand pull-left")(
          a(href := "/")("Black Box Society")
        ),
        div(`class`:="identity-box pull-right")
      ),
      view_body
    )
  )

  def view_body: HtmlTag

  override def toString = s"<!DOCTYPE html>\n${render.toString()}"

}

case class Home() extends Layout {

  def view_body: HtmlTag = h1("Welcome to black box society!")

}
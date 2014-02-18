package com.blackboxsociety.app.views

import com.blackboxsociety.mvc._
import scalatags.Tags2.title
import scalatags.HtmlTag
import scalatags.all._

trait Layout extends ScalaView {

  def render: HtmlTag = html(
    head(
      title("Black Box Society"),
      meta(charset := "utf-8"),
      meta(name := "viewport", content := "width=device-width, initial-scale=1.0"),
      meta(name := "description", content := "Spreading and connecting information"),
      link(rel := "stylesheet", `type` := "text/css", href := "/assets/css/main.css")
    ),
    body(
      div(`class`:="top nav")(
        div(`class`:="brand pull-left")(
          a(href := "/")("Black Box Society")
        ),
        div(`class`:="identity-box pull-right")
      ),
      container
    )
  )

  def container: HtmlTag

  override def toString = s"<!DOCTYPE html>\n${render.toString()}"

}

case class Home() extends Layout {

  def container: HtmlTag = h1(`class` := "blue")("Welcome to black box society!")

}
package com.blackboxsociety.app.views

import com.blackboxsociety.mvc._
import scala.xml.Elem

trait Layout extends HtmlView {

  def render: Elem =
    <html>
      <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Spreading and connecting information" />
        <link rel="stylesheet" type="text/css" href="/assets/css/main.css" />
        <title>Black Box Society</title>
      </head>
      <body>
        <div class="top nav">
          <div class="brand pull-left">
            <a href="/">Black Box Society</a>
          </div>
          <div class="identity-box pull-right"></div>
        </div>
        {container}
      </body>
    </html>

  def container: Elem

  override def toString = s"<!DOCTYPE html>\n${render.toString()}"

}

case class Home() extends Layout {

  def container: Elem = <h1 class="blue">Welcome to black box society!</h1>

}
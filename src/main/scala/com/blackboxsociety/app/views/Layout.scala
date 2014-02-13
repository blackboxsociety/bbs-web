package com.blackboxsociety.app.views

import scala.xml._
import com.blackboxsociety.mvc._

trait Layout extends View {

  def render: Elem =
    <html>
      <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta name="description" content="Spreading and connecting information" />
        <title>Black Box Society</title>
      </head>
      <body>
        <div class="top nav">
          <div class="brand pull-left">
            <a href="/">Black Box Society</a>
          </div>
          <div class="identity-box pull-right"></div>
        </div>
        {body}
      </body>
    </html>

  def body: Elem

  override def toString = s"<!DOCTYPE html>\n${render.toString()}"

}

case class Home() extends Layout {

  def body: Elem = <h1>Welcome to black box society!</h1>

}
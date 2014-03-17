package com.blackboxsociety.net.webservices

sealed trait AuthType {

  def apply(username: String, password: String): String

}

case object Basic extends AuthType {

  def apply(username: String, password: String): String = {
    "Basic " + username + ":" + password // turn username:password into RFC2045-MIME base64
  }

}


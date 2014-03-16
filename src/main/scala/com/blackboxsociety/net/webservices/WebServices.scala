package com.blackboxsociety.net.webservices

import com.blackboxsociety.http.HttpGenericHeader

object WebServices {

  def request(url: String): ClientHttpRequest = {
    ClientHttpRequest(url, Seq[HttpGenericHeader]())
  }

}

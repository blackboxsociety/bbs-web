package com.blackboxsociety.http

case class HttpResource(path: String, queryString: Option[String]) {

  val queryParams: Map[String, String] = HttpResource.queryStringToMap(queryString.getOrElse(""))

  def getParam(key: String): Option[String] = {
    queryParams.get(key)
  }

}

object HttpResource {

  def queryStringToMap(q: String): Map[String, String] = {
    if(q == "") {
      Map()
    } else {
      def ands = q.split("&")
      def es: Array[Array[String]] = ands.map(_.split("="))
      es.map(s => (s(0), s(1))).toMap
    }
  }

}
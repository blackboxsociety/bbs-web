package com.blackboxsociety.http.routes

import org.specs2.mutable._
import com.blackboxsociety.http._
import com.blackboxsociety.util.parser._

class RegexPathRouteSpec extends Specification {

  val routeRule = RegexPathRoute("/posts/([0-9]+)", "id")

  val matchingRequest = HttpRequest(
    method   = HttpGet,
    resource = HttpResource("/posts/1337"),
    version  = HttpVersionOneDotOne,
    headers  = List(),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val nonMatchingRequest = HttpRequest(
    method   = HttpGet,
    resource = HttpResource("/login"),
    version  = HttpVersionOneDotOne,
    headers  = List(),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val resultOfMatchRequest = HttpRequest(
    method   = HttpGet,
    resource = HttpResource("/posts/1337"),
    version  = HttpVersionOneDotOne,
    headers  = List(),
    body     = VoidParserStream,
    pathVars = Map("id" -> "1337")
  )

  "The method route config of RegexPathRoute(\"/posts/([0-9]+)\", \"id\")" should {
    "match a request with the path /posts/1337 and have it's parameters" in {
      val result = routeRule.route(matchingRequest)
      result must beAnInstanceOf[Some[HttpRequest]]
      result.get.pathVars must beEqualTo(Map("id" -> "1337"))
    }
    "not match a request with the path /login" in {
      routeRule.route(nonMatchingRequest) must beEmpty
    }
  }



}

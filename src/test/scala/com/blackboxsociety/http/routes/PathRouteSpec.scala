package com.blackboxsociety.http.routes

import org.specs2.mutable._
import com.blackboxsociety.http._
import com.blackboxsociety.util.parser._

class PathRouteSpec extends Specification {

  val routeRule = PathRoute("/login")

  val matchingRequest = HttpRequest(
    method   = HttpGet,
    resource = HttpResource("/login"),
    version  = HttpVersionOneDotOne,
    headers  = List(),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val nonMatchingRequest = HttpRequest(
    method   = HttpGet,
    resource = HttpResource("/register"),
    version  = HttpVersionOneDotOne,
    headers  = List(),
    body     = VoidParserStream,
    pathVars = Map()
  )

  "The method route config of PathRoute(\"/login\")" should {
    "match a request with the path of \"/login\"" in {
      routeRule.route(matchingRequest) must beEqualTo(Some(matchingRequest))
    }
    "not match a request with the path of \"/register\"" in {
      routeRule.route(nonMatchingRequest) must beEqualTo(None)
    }
  }

}

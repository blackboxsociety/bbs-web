package com.blackboxsociety.http.routes

import org.specs2.mutable._
import com.blackboxsociety.http._
import com.blackboxsociety.util.parser._

class VhostRouteSpec extends Specification {

  val routeRule = VhostRoute("blackboxsociety.com")

  val matchingRequest = HttpRequest(
    method   = HttpGet,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val nonMatchingRequest = HttpRequest(
    method   = HttpGet,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "google.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val noHostRequest = HttpRequest(
    method   = HttpGet,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(),
    body     = VoidParserStream,
    pathVars = Map()
  )

  "The vhost route config of VhostRoute(\"blackboxsociety.com\")" should {
    "match a request with the host header \"blackboxsociety.com\"" in {
      routeRule.route(matchingRequest) must beEqualTo(Some(matchingRequest))
    }
    "not match a request with the host header \"google.com\"" in {
      routeRule.route(nonMatchingRequest) must beEqualTo(None)
    }
    "not match a request with a missing host header" in {
      routeRule.route(noHostRequest) must beEqualTo(None)
    }
  }

}

package com.blackboxsociety.http.routes

import org.specs2.mutable._
import com.blackboxsociety.http._
import com.blackboxsociety.util.parser._

class MethodRouteSpec extends Specification {

  val connectRule = MethodRoute(HttpConnect)
  val deleteRule  = MethodRoute(HttpDelete)
  val getRule     = MethodRoute(HttpGet)
  val headRule    = MethodRoute(HttpHead)
  val optionsRule = MethodRoute(HttpOptions)
  val patchRule   = MethodRoute(HttpPatch)
  val postRule    = MethodRoute(HttpPost)
  val putRule     = MethodRoute(HttpPut)
  val traceRule   = MethodRoute(HttpTrace)

  val connectRequest = HttpRequest(
    method   = HttpConnect,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val deleteRequest = HttpRequest(
    method   = HttpDelete,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val getRequest = HttpRequest(
    method   = HttpGet,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val headRequest = HttpRequest(
    method   = HttpHead,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val optionsRequest = HttpRequest(
    method   = HttpOptions,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val patchRequest = HttpRequest(
    method   = HttpPatch,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val postRequest = HttpRequest(
    method   = HttpPost,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val putRequest = HttpRequest(
    method   = HttpPut,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  val traceRequest = HttpRequest(
    method   = HttpTrace,
    resource = HttpResource("/"),
    version  = HttpVersionOneDotOne,
    headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
    body     = VoidParserStream,
    pathVars = Map()
  )

  "The method route config of MethodRoute(HttpConnect)" should {
    "match a request with a method of CONNECT" in {
      connectRule.route(connectRequest) must beEqualTo(Some(connectRequest))
    }
    "not match a request with a method of DELETE" in {
      connectRule.route(deleteRequest) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      connectRule.route(getRequest) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      connectRule.route(headRequest) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      connectRule.route(optionsRequest) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      connectRule.route(patchRequest) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      connectRule.route(postRequest) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      connectRule.route(putRequest) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      connectRule.route(traceRequest) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpDelete)" should {
    "not match a request with a method of CONNECT" in {
      deleteRule.route(connectRequest) must beEqualTo(None)
    }
    "match a request with a method of DELETE" in {
      deleteRule.route(deleteRequest) must beEqualTo(Some(deleteRequest))
    }
    "not match a request with a method of GET" in {
      deleteRule.route(getRequest) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      deleteRule.route(headRequest) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      deleteRule.route(optionsRequest) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      deleteRule.route(patchRequest) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      deleteRule.route(postRequest) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      deleteRule.route(putRequest) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      deleteRule.route(traceRequest) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpGet)" should {
    "not match a request with a method of CONNECT" in {
      getRule.route(connectRequest) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      getRule.route(deleteRequest) must beEqualTo(None)
    }
    "match a request with a method of GET" in {
      getRule.route(getRequest) must beEqualTo(Some(getRequest))
    }
    "not match a request with a method of HEAD" in {
      getRule.route(headRequest) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      getRule.route(optionsRequest) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      getRule.route(patchRequest) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      getRule.route(postRequest) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      getRule.route(putRequest) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      getRule.route(traceRequest) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpHead)" should {
    "not match a request with a method of CONNECT" in {
      headRule.route(connectRequest) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      headRule.route(deleteRequest) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      headRule.route(getRequest) must beEqualTo(None)
    }
    "match a request with a method of HEAD" in {
      headRule.route(headRequest) must beEqualTo(Some(headRequest))
    }
    "not match a request with a method of OPTIONS" in {
      headRule.route(optionsRequest) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      headRule.route(patchRequest) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      headRule.route(postRequest) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      headRule.route(putRequest) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      headRule.route(traceRequest) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpOptions)" should {
    "not match a request with a method of CONNECT" in {
      optionsRule.route(connectRequest) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      optionsRule.route(deleteRequest) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      optionsRule.route(getRequest) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      optionsRule.route(headRequest) must beEqualTo(None)
    }
    "match a request with a method of OPTIONS" in {
      optionsRule.route(optionsRequest) must beEqualTo(Some(optionsRequest))
    }
    "not match a request with a method of PATCH" in {
      optionsRule.route(patchRequest) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      optionsRule.route(postRequest) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      optionsRule.route(putRequest) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      optionsRule.route(traceRequest) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpPatch)" should {
    "not match a request with a method of CONNECT" in {
      patchRule.route(connectRequest) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      patchRule.route(deleteRequest) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      patchRule.route(getRequest) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      patchRule.route(headRequest) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      patchRule.route(optionsRequest) must beEqualTo(None)
    }
    "match a request with a method of PATCH" in {
      patchRule.route(patchRequest) must beEqualTo(Some(patchRequest))
    }
    "not match a request with a method of POST" in {
      patchRule.route(postRequest) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      patchRule.route(putRequest) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      patchRule.route(traceRequest) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpPost)" should {
    "not match a request with a method of CONNECT" in {
      postRule.route(connectRequest) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      postRule.route(deleteRequest) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      postRule.route(getRequest) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      postRule.route(headRequest) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      postRule.route(optionsRequest) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      postRule.route(patchRequest) must beEqualTo(None)
    }
    "match a request with a method of POST" in {
      postRule.route(postRequest) must beEqualTo(Some(postRequest))
    }
    "not match a request with a method of PUT" in {
      postRule.route(putRequest) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      postRule.route(traceRequest) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpPut)" should {
    "not match a request with a method of CONNECT" in {
      putRule.route(connectRequest) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      putRule.route(deleteRequest) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      putRule.route(getRequest) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      putRule.route(headRequest) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      putRule.route(optionsRequest) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      putRule.route(patchRequest) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      putRule.route(postRequest) must beEqualTo(None)
    }
    "match a request with a method of PUT" in {
      putRule.route(putRequest) must beEqualTo(Some(putRequest))
    }
    "not match a request with a method of TRACE" in {
      putRule.route(traceRequest) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpTrace)" should {
    "not match a request with a method of CONNECT" in {
      traceRule.route(connectRequest) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      traceRule.route(deleteRequest) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      traceRule.route(getRequest) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      traceRule.route(headRequest) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      traceRule.route(optionsRequest) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      traceRule.route(patchRequest) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      traceRule.route(postRequest) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      traceRule.route(putRequest) must beEqualTo(None)
    }
    "match a request with a method of TRACE" in {
      traceRule.route(traceRequest) must beEqualTo(Some(traceRequest))
    }
  }

}

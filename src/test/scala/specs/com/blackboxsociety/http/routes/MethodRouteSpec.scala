package specs.com.blackboxsociety.http.routes

import org.specs2.mutable._
import com.blackboxsociety.http._
import fixtures.http.requests.Index._
import com.blackboxsociety.http.routes._

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

  "The method route config of MethodRoute(HttpConnect)" should {
    "match a request with a method of CONNECT" in {
      connectRule.route(connectRequest.parsed) must beEqualTo(Some(connectRequest.parsed))
    }
    "not match a request with a method of DELETE" in {
      connectRule.route(deleteRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      connectRule.route(getRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      connectRule.route(headRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      connectRule.route(optionsRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      connectRule.route(patchRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      connectRule.route(postRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      connectRule.route(putRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      connectRule.route(traceRequest.parsed) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpDelete)" should {
    "not match a request with a method of CONNECT" in {
      deleteRule.route(connectRequest.parsed) must beEqualTo(None)
    }
    "match a request with a method of DELETE" in {
      deleteRule.route(deleteRequest.parsed) must beEqualTo(Some(deleteRequest.parsed))
    }
    "not match a request with a method of GET" in {
      deleteRule.route(getRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      deleteRule.route(headRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      deleteRule.route(optionsRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      deleteRule.route(patchRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      deleteRule.route(postRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      deleteRule.route(putRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      deleteRule.route(traceRequest.parsed) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpGet)" should {
    "not match a request with a method of CONNECT" in {
      getRule.route(connectRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      getRule.route(deleteRequest.parsed) must beEqualTo(None)
    }
    "match a request with a method of GET" in {
      getRule.route(getRequest.parsed) must beEqualTo(Some(getRequest.parsed))
    }
    "not match a request with a method of HEAD" in {
      getRule.route(headRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      getRule.route(optionsRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      getRule.route(patchRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      getRule.route(postRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      getRule.route(putRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      getRule.route(traceRequest.parsed) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpHead)" should {
    "not match a request with a method of CONNECT" in {
      headRule.route(connectRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      headRule.route(deleteRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      headRule.route(getRequest.parsed) must beEqualTo(None)
    }
    "match a request with a method of HEAD" in {
      headRule.route(headRequest.parsed) must beEqualTo(Some(headRequest.parsed))
    }
    "not match a request with a method of OPTIONS" in {
      headRule.route(optionsRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      headRule.route(patchRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      headRule.route(postRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      headRule.route(putRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      headRule.route(traceRequest.parsed) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpOptions)" should {
    "not match a request with a method of CONNECT" in {
      optionsRule.route(connectRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      optionsRule.route(deleteRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      optionsRule.route(getRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      optionsRule.route(headRequest.parsed) must beEqualTo(None)
    }
    "match a request with a method of OPTIONS" in {
      optionsRule.route(optionsRequest.parsed) must beEqualTo(Some(optionsRequest.parsed))
    }
    "not match a request with a method of PATCH" in {
      optionsRule.route(patchRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      optionsRule.route(postRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      optionsRule.route(putRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      optionsRule.route(traceRequest.parsed) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpPatch)" should {
    "not match a request with a method of CONNECT" in {
      patchRule.route(connectRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      patchRule.route(deleteRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      patchRule.route(getRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      patchRule.route(headRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      patchRule.route(optionsRequest.parsed) must beEqualTo(None)
    }
    "match a request with a method of PATCH" in {
      patchRule.route(patchRequest.parsed) must beEqualTo(Some(patchRequest.parsed))
    }
    "not match a request with a method of POST" in {
      patchRule.route(postRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      patchRule.route(putRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      patchRule.route(traceRequest.parsed) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpPost)" should {
    "not match a request with a method of CONNECT" in {
      postRule.route(connectRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      postRule.route(deleteRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      postRule.route(getRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      postRule.route(headRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      postRule.route(optionsRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      postRule.route(patchRequest.parsed) must beEqualTo(None)
    }
    "match a request with a method of POST" in {
      postRule.route(postRequest.parsed) must beEqualTo(Some(postRequest.parsed))
    }
    "not match a request with a method of PUT" in {
      postRule.route(putRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of TRACE" in {
      postRule.route(traceRequest.parsed) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpPut)" should {
    "not match a request with a method of CONNECT" in {
      putRule.route(connectRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      putRule.route(deleteRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      putRule.route(getRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      putRule.route(headRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      putRule.route(optionsRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      putRule.route(patchRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      putRule.route(postRequest.parsed) must beEqualTo(None)
    }
    "match a request with a method of PUT" in {
      putRule.route(putRequest.parsed) must beEqualTo(Some(putRequest.parsed))
    }
    "not match a request with a method of TRACE" in {
      putRule.route(traceRequest.parsed) must beEqualTo(None)
    }
  }
  "The method route config of MethodRoute(HttpTrace)" should {
    "not match a request with a method of CONNECT" in {
      traceRule.route(connectRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of DELETE" in {
      traceRule.route(deleteRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of GET" in {
      traceRule.route(getRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of HEAD" in {
      traceRule.route(headRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of OPTIONS" in {
      traceRule.route(optionsRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PATCH" in {
      traceRule.route(patchRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of POST" in {
      traceRule.route(postRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a method of PUT" in {
      traceRule.route(putRequest.parsed) must beEqualTo(None)
    }
    "match a request with a method of TRACE" in {
      traceRule.route(traceRequest.parsed) must beEqualTo(Some(traceRequest.parsed))
    }
  }

}

package specs.com.blackboxsociety.http.routes

import org.specs2.mutable._
import com.blackboxsociety.http.routes._
import com.blackboxsociety.http._
import fixtures.http.requests._

class HttpRouteSpec extends Specification {

  val routeRule = HttpRoute(MethodRoute(HttpGet), PathRoute("/"))

  "The route HttpRoute(MethodRoute(HttpGet), PathRoute(\"/\"))" should {
    "match a GET request to the path /" in {
      routeRule.route(Index.getRequest.parsed) must beEqualTo(Some(Index.getRequest.parsed))
    }
    "not match a GET request to the path /login" in {
      routeRule.route(Login.getRequest.parsed) must beEmpty
    }
    "not match a POST request to the path /" in {
      routeRule.route(Index.postRequest.parsed) must beEmpty
    }
  }

}
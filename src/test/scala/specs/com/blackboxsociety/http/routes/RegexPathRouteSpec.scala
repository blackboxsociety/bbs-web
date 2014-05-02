package specs.com.blackboxsociety.http.routes

import org.specs2.mutable._
import fixtures.http.requests._
import com.blackboxsociety.http._
import com.blackboxsociety.http.routes._

class RegexPathRouteSpec extends Specification {

  val routeRule = RegexPathRoute("/posts/([0-9]+)", "id")

  "The method route config of RegexPathRoute(\"/posts/([0-9]+)\", \"id\")" should {
    "match a request with the path /posts/1337 and have it's parameters" in {
      val result = routeRule.route(Posts.getWithParameterRequest.parsed)
      result must beAnInstanceOf[Some[HttpRequest]]
      result.get.pathVars must beEqualTo(Map("id" -> "1337"))
    }
    "not match a request with the path /login" in {
      routeRule.route(Login.getRequest.parsed) must beEmpty
    }
  }

}

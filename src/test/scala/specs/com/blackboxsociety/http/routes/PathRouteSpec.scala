package specs.com.blackboxsociety.http.routes

import org.specs2.mutable._
import fixtures.http.requests._
import com.blackboxsociety.http.routes._

class PathRouteSpec extends Specification {

  val routeRule = PathRoute("/login")

  "The method route config of PathRoute(\"/login\")" should {
    "match a request with the path of \"/login\"" in {
      routeRule.route(Login.getRequest.parsed) must beEqualTo(Some(Login.getRequest.parsed))
    }
    "not match a request with the path of \"/register\"" in {
      routeRule.route(Register.getRequest.parsed) must beEqualTo(None)
    }
  }

}

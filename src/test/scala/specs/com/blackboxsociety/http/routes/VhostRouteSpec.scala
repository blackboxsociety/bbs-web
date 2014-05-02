package specs.com.blackboxsociety.http.routes

import org.specs2.mutable._
import fixtures.http.requests.Index._
import com.blackboxsociety.http.routes._

class VhostRouteSpec extends Specification {

  val routeRule = VhostRoute("blackboxsociety.com")

  "The vhost route config of VhostRoute(\"blackboxsociety.com\")" should {
    "match a request with the host header \"blackboxsociety.com\"" in {
      routeRule.route(getRequest.parsed) must beEqualTo(Some(getRequest.parsed))
    }
    "not match a request with the host header \"google.com\"" in {
      routeRule.route(getWithGoogleHostRequest.parsed) must beEqualTo(None)
    }
    "not match a request with a missing host header" in {
      routeRule.route(getWithNoHostRequest.parsed) must beEqualTo(None)
    }
  }

}

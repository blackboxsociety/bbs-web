package fixtures.http.requests

import com.blackboxsociety.http.HttpRequest

trait HttpRequestFixtureContainer {

  case class HttpRequestPair(raw: String, parsed: HttpRequest)

}

package fixtures.http.requests

import com.blackboxsociety.http._
import com.blackboxsociety.util.parser._

object Login extends HttpRequestFixtureContainer {

  val getRequest = HttpRequestPair(
    "GET /login HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpGet,
      resource = HttpResource("/login"),
      version  = HttpVersionOneDotOne,
      headers  = List(),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

}

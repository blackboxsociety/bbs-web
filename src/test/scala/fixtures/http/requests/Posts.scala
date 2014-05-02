package fixtures.http.requests

import com.blackboxsociety.http._
import com.blackboxsociety.util.parser._

object Posts extends HttpRequestFixtureContainer {

  val getWithParameterRequest = HttpRequestPair(
    "GET /posts/1337 HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpGet,
      resource = HttpResource("/posts/1337"),
      version  = HttpVersionOneDotOne,
      headers  = List(),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val getWithParameterMatchedRequest = HttpRequestPair(
    "GET /posts/1337 HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpGet,
      resource = HttpResource("/posts/1337"),
      version  = HttpVersionOneDotOne,
      headers  = List(),
      body     = StringParserStream(""),
      pathVars = Map("id" -> "1337")
    )
  )

}

package fixtures.http.requests

import com.blackboxsociety.http._
import com.blackboxsociety.util.parser._

object Index extends HttpRequestFixtureContainer {

  val connectRequest = HttpRequestPair(
    "CONNECT / HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpConnect,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val deleteRequest = HttpRequestPair(
    "DELETE / HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpDelete,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val getRequest = HttpRequestPair(
    "GET / HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpGet,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val headRequest = HttpRequestPair(
    "HEAD / HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpHead,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val optionsRequest = HttpRequestPair(
    "OPTIONS / HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpOptions,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val patchRequest = HttpRequestPair(
    "PATCH / HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpPatch,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val postRequest = HttpRequestPair(
    "POST / HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpPost,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val putRequest = HttpRequestPair(
    "PUT / HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpPut,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val traceRequest = HttpRequestPair(
    "TRACE / HTTP/1.1\r\nHost: blackboxsociety.com\r\n\r\n",
    HttpRequest(
      method   = HttpTrace,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "blackboxsociety.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val getWithNoHostRequest = HttpRequestPair(
    "GET / HTTP/1.1\r\n\r\n",
    HttpRequest(
      method   = HttpGet,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val getWithGoogleHostRequest = HttpRequestPair(
    "TRACE / HTTP/1.1\r\nHost: google.com\r\n\r\n",
    HttpRequest(
      method   = HttpGet,
      resource = HttpResource("/"),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "google.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  val getWithQueryString = HttpRequestPair(
    "GET /?key=value HTTP/1.1\r\nHost: google.com\r\n\r\n",
    HttpRequest(
      method   = HttpGet,
      resource = HttpResource("/", Some("key=value")),
      version  = HttpVersionOneDotOne,
      headers  = List(HttpGenericHeader("Host", "google.com")),
      body     = StringParserStream(""),
      pathVars = Map()
    )
  )

  case class HttpRequestErrorPair(raw: String, error: String)

  val getWithInvalidMethod = HttpRequestErrorPair(
    raw   = "WELP / HTTP/1.1\r\nHost: google.com\r\n\r\n",
    error = "`PATCH' expected but `W' found"
  )

  val getWithInvalidResource = HttpRequestErrorPair(
    raw   = "GET !!! HTTP/1.1\r\nHost: google.com\r\n\r\n",
    error = "string matching regex `[A-Za-z0-9/_.-]+' expected but `!' found"
  )

  val getWithInvalidVersion = HttpRequestErrorPair(
    raw   = "GET / HTTP/BOOM\r\nHost: google.com\r\n\r\n",
    error = "`HTTP/1.1' expected but `H' found"
  )

  val getWithMissingLineAfterVersion = HttpRequestErrorPair(
    raw   = "GET / HTTP/1.1Host: google.com\r\n\r\n",
    error = "`\r\n' expected but `H' found"
  )

  val getWithInvalidHeader = HttpRequestErrorPair(
    raw   = "GET / HTTP/1.1\r\nLOLWAT\r\n\r\n",
    error = "`\r\n' expected but `L' found"
  )

  val getWithMissingTrailingLine = HttpRequestErrorPair(
    raw   = "GET / HTTP/1.1\r\nHost: google.com\r\n",
    error = "Received an incomplete HTTP request."
  )

}

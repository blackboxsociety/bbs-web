package specs.com.blackboxsociety.http

import org.specs2.mutable._
import fixtures.http.requests._
import com.blackboxsociety.http._
import scalaz.{Index => _, _}

class HttpParserSpec extends Specification {

  "A valid HTTP CONNECT request to the / with a host header" should {
    "correctly parse the request" in {
      HttpParser(Index.connectRequest.raw).attemptRun must beEqualTo(\/-(Index.connectRequest.parsed))
    }
  }

  "A valid HTTP DELETE request to the / with a host header" should {
    "correctly parse the request" in {
      HttpParser(Index.deleteRequest.raw).attemptRun must beEqualTo(\/-(Index.deleteRequest.parsed))
    }
  }

  "A valid HTTP GET request to the / with a host header" should {
    "correctly parse the request" in {
      HttpParser(Index.getRequest.raw).attemptRun must beEqualTo(\/-(Index.getRequest.parsed))
    }
  }

  "A valid HTTP HEAD request to the / with a host header" should {
    "correctly parse the request" in {
      HttpParser(Index.headRequest.raw).attemptRun must beEqualTo(\/-(Index.headRequest.parsed))
    }
  }

  "A valid HTTP OPTIONS request to the / with a host header" should {
    "correctly parse the request" in {
      HttpParser(Index.optionsRequest.raw).attemptRun must beEqualTo(\/-(Index.optionsRequest.parsed))
    }
  }

  "A valid HTTP PATCH request to the / with a host header" should {
    "correctly parse the request" in {
      HttpParser(Index.patchRequest.raw).attemptRun must beEqualTo(\/-(Index.patchRequest.parsed))
    }
  }

  "A valid HTTP POST request to the / with a host header" should {
    "correctly parse the request" in {
      HttpParser(Index.postRequest.raw).attemptRun must beEqualTo(\/-(Index.postRequest.parsed))
    }
  }

  "A valid HTTP PUT request to the / with a host header" should {
    "correctly parse the request" in {
      HttpParser(Index.putRequest.raw).attemptRun must beEqualTo(\/-(Index.putRequest.parsed))
    }
  }

  "A valid HTTP TRACE request to the / with a host header" should {
    "correctly parse the request" in {
      HttpParser(Index.traceRequest.raw).attemptRun must beEqualTo(\/-(Index.traceRequest.parsed))
    }
  }

  "A valid HTTP GET request to the / with a query string" should {
    "correctly parse the request" in {
      HttpParser(Index.getWithQueryString.raw).attemptRun must beEqualTo(\/-(Index.getWithQueryString.parsed))
    }
  }

  "An invalid HTTP WELP request to the / with a host header" should {
    "error out properly" in {
      val result = HttpParser(Index.getWithInvalidMethod.raw).attemptRun
      result must beAnInstanceOf[-\/[HttpParserException]]
      result.toEither.swap.right.get.asInstanceOf[HttpParserException].s must beEqualTo(Index.getWithInvalidMethod.error)
    }
  }

  "A HTTP GET request with an invalid resource" should {
    "error out properly" in {
      val result = HttpParser(Index.getWithInvalidResource.raw).attemptRun
      result must beAnInstanceOf[-\/[HttpParserException]]
      result.toEither.swap.right.get.asInstanceOf[HttpParserException].s must beEqualTo(Index.getWithInvalidResource.error)
    }
  }

  "A HTTP GET request with an invalid version" should {
    "error out properly" in {
      val result = HttpParser(Index.getWithInvalidVersion.raw).attemptRun
      result must beAnInstanceOf[-\/[HttpParserException]]
      result.toEither.swap.right.get.asInstanceOf[HttpParserException].s must beEqualTo(Index.getWithInvalidVersion.error)
    }
  }

  "A HTTP GET request with a missing new line after the version" should {
    "error out properly" in {
      val result = HttpParser(Index.getWithMissingLineAfterVersion.raw).attemptRun
      result must beAnInstanceOf[-\/[HttpParserException]]
      result.toEither.swap.right.get.asInstanceOf[HttpParserException].s must beEqualTo(Index.getWithMissingLineAfterVersion.error)
    }
  }

  "A HTTP GET request with an invalid header" should {
    "error out properly" in {
      val result = HttpParser(Index.getWithInvalidHeader.raw).attemptRun
      result must beAnInstanceOf[-\/[HttpParserException]]
      result.toEither.swap.right.get.asInstanceOf[HttpParserException].s must beEqualTo(Index.getWithInvalidHeader.error)
    }
  }

  "A HTTP GET request with a missing trailing new line" should {
    "error out properly" in {
      val result = HttpParser(Index.getWithMissingTrailingLine.raw).attemptRun
      result must beAnInstanceOf[-\/[HttpParserException]]
      result.toEither.swap.right.get.asInstanceOf[HttpParserException].s must beEqualTo(Index.getWithMissingTrailingLine.error)
    }
  }

}

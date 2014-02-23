package com.blackboxsociety.http

import scala.util.parsing.combinator._
import com.blackboxsociety.net._
import scalaz.concurrent._
import scalaz.concurrent.Task._

case class HttpParserException(s: String) extends Throwable

object HttpParser extends RegexParsers {

  override def skipWhitespace = false

  def spaces = """[ ]+""".r

  def nonSpaces = """[^ ]+""".r

  def lineEnd = "\r\n"

  def nonEndings = "[^\r\n]+".r

  def httpGet: Parser[HttpMethod] = "GET" ^^ { _ =>
    HttpGet
  }

  def httpHead: Parser[HttpMethod] = "HEAD" ^^ { _ =>
    HttpHead
  }

  def httpPost: Parser[HttpMethod] = "POST" ^^ { _ =>
    HttpPost
  }

  def httpPut: Parser[HttpMethod] = "PUT" ^^ { _ =>
    HttpPut
  }

  def httpDelete: Parser[HttpMethod] = "DELETE" ^^ { _ =>
    HttpDelete
  }

  def httpTrace: Parser[HttpMethod] = "TRACE" ^^ { _ =>
    HttpTrace
  }

  def httpOptions: Parser[HttpMethod] = "OPTIONS" ^^ { _ =>
    HttpOptions
  }

  def httpConnect: Parser[HttpMethod] = "CONNECT" ^^ { _ =>
    HttpConnect
  }

  def httpPatch: Parser[HttpMethod] = "PATCH" ^^ { _ =>
    HttpPatch
  }

  def httpResource: Parser[HttpResource] = nonSpaces ^^ { n =>
    HttpResource(n)
  }

  def httpMethod: Parser[HttpMethod] = httpGet     |
                                       httpHead    |
                                       httpPost    |
                                       httpPut     |
                                       httpDelete  |
                                       httpTrace   |
                                       httpOptions |
                                       httpConnect |
                                       httpPatch

  def httpVersionLegacy: Parser[HttpVersion] = "HTTP/1.0" ^^ { _ =>
    HttpVersionOneDotZero
  }

  def httpVersionModern: Parser[HttpVersion] = "HTTP/1.1" ^^ { _ =>
    HttpVersionOneDotOne
  }

  def httpVersion: Parser[HttpVersion] = httpVersionLegacy | httpVersionModern

  def httpHeader: Parser[HttpHeader] = "[^:\r\n]+".r ~ ":" ~ spaces ~ nonEndings ~ lineEnd ^^ {
    case (key ~ _ ~ _ ~ value ~ _) => HttpHeader(key, value)
  }

  def httpParser: Parser[HttpRequest] = httpMethod       ~
                                        " "              ~
                                        httpResource     ~
                                        " "              ~
                                        httpVersion      ~
                                        lineEnd          ~
                                        rep(httpHeader)  ~
                                        lineEnd          ^^
    {
      case (method ~ _ ~ resource ~ _ ~ version ~ _ ~ headers ~  _) => HttpRequest(
        method,
        resource,
        version,
        headers
      )
    }

  def apply(client: TcpClient, previous: String = ""): Task[HttpRequest] = {
    client.readAsString() flatMap { s =>
      val current = previous + s
      parse(httpParser, current) match {
        case Success(request, next) => now { request }
        case NoSuccess(error, _)    => "source found$".r findFirstIn error match {
          case None => fail(HttpParserException(error))
          case _    => apply(client, current)
        }
      }
    }
  }

}
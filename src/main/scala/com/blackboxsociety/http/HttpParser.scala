package com.blackboxsociety.http

import scala.util.parsing.combinator._
import scalaz.concurrent._
import scalaz.concurrent.Task._
import com.blackboxsociety.util.parser.{TcpParserStream, ParserStream}

case class HttpParserException(s: String) extends Throwable

case class ParsedHttpRequest(method:   HttpMethod,
                             resource: HttpResource,
                             version:  HttpVersion,
                             headers:  List[HttpHeader])

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
    case (key ~ _ ~ _ ~ value ~ _) => HttpGenericHeader(key, value)
  }

  def httpParser: Parser[ParsedHttpRequest] = httpMethod       ~
                                              " "              ~
                                              httpResource     ~
                                              " "              ~
                                              httpVersion      ~
                                              lineEnd          ~
                                              rep(httpHeader)  ~
                                              lineEnd          ^^
    {
      case (method ~ _ ~ resource ~ _ ~ version ~ _ ~ headers ~  _) => ParsedHttpRequest(
        method,
        resource,
        version,
        headers
      )
    }

  def apply(stream: ParserStream): Task[HttpRequest] = {
    stream.latest flatMap { s =>
      parse(httpParser, s.current) match {
        case Success(request, next) => now {
          HttpRequest(
            method   = request.method,
            resource = request.resource,
            version  = request.version,
            headers  = request.headers,
            body     = s.withText(next.source.toString.substring(next.offset))
          )
        }
        case NoSuccess(error, _)    => "source found$".r findFirstIn error match {
          case None => fail(HttpParserException(error))
          case _    => apply(s)
        }
      }
    }
  }

}
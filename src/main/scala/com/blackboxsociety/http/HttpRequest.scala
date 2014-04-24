package com.blackboxsociety.http

import com.blackboxsociety.util.parser.ParserStream
import scalaz.concurrent.Task
import com.blackboxsociety.util.{More, Done}
import com.blackboxsociety.security.crypto.SignedMap
import com.blackboxsociety.http.routes.{RegexPathRoute, HttpRouteRule}
import scala.util.matching.Regex

case class HttpRequest(method:   HttpMethod,
                       resource: HttpResource,
                       version:  HttpVersion,
                       headers:  List[HttpHeader],
                       body:     ParserStream,
                       pathVars: Map[String, String] = Map())
{

  def session(secret: String): SignedSession = {
    SignedSession(secret, readSignedCookie(secret, "session"))
  }

  def flash(secret: String): FlashSession = {
    FlashSession(secret, readSignedCookie(secret, "flash"))
  }

  private def readSignedCookie(key: String, secret: String): Map[String, String] = {
    headers
      .filter(_.key == "Cookie")
      .map({ n => Cookie.parse(n.value) })
      .filter(_.size > 0)
      .find(_.exists({ case (k, _) => k == key }))
      .flatMap(_.get(key))
      .flatMap(SignedMap.verify(secret, _))
      .getOrElse(Map())
  }

  def getPathVar(key: String): Option[String] = {
    pathVars.get(key)
  }

  def contentLength(): Option[Int] = {
    headers.find(h => h.key == "Content-Length").map(_.value.toInt)
  }

  def getBody(): Task[String] = {
    body.current match {
      case Done(d: String) =>
        Task.now{d}
      case More(m: String) =>
        this.contentLength().map { l =>
          if(m.length > l) {
            Task.now{m}
          } else {
            readBody(body)
          }
        }.getOrElse(readBody(body))
    }
  }

  private def readBody(ps: ParserStream): Task[String] = {
    ps.latest.flatMap { p => {
      p.current match {
        case Done(d: String) =>
          Task.now{d}
        case More(m: String) =>
          readBody(p)
        }
    }}
  }

}

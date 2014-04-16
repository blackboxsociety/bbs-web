package com.blackboxsociety.http

import play.api.libs.json.{JsString, JsObject, Json}
import com.blackboxsociety.util.parser.ParserStream
import scalaz.concurrent.Task
import com.blackboxsociety.util.{More, Done}

case class HttpRequest(method:   HttpMethod,
                       resource: HttpResource,
                       version:  HttpVersion,
                       headers:  List[HttpHeader],
                       body:     ParserStream)
{

  lazy val session = headers
    .filter(_.key == "Cookie")
    .map({ n => Cookie.parse(n.value) })
    .filter(_.size > 0)
    .find(_.exists({ case (k, _) => k == "session" }))
    .flatMap(_.get("session"))
    .map({ n => Json.parse(n.drop(64)) })
    .flatMap({
      case JsObject(f) =>
        f.foldLeft[Option[Map[String, String]]](Some(Map())) { (m, n) =>
          m.flatMap { o =>
            n._2 match {
              case JsString(s) => Some(o + (n._1 -> s))
              case _           => None
            }
          }
        }
      case _ => None
    })
    .getOrElse(Map())

  def getBody(ps: ParserStream = body): Task[String] = {
    ps.latest.flatMap { p => (
      p.current match {
        case Done(d: String) =>
          Task.now{d}
        case More(m: String) =>
          getBody(p)
      }
    )}
  }

}
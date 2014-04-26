package com.blackboxsociety.services

import com.blackboxsociety.util._
import scalaz.ImmutableArray
import scalaz.concurrent._
import scalaz.syntax.bind._
import com.blackboxsociety.net._
import com.blackboxsociety.http._
import com.blackboxsociety.util.parser.TcpParserStream._
import java.io._
import com.blackboxsociety.http.HttpRequest
import com.blackboxsociety.http.HttpParserException
import com.blackboxsociety.http.InternalServerError
import com.blackboxsociety.http.MissingRouteException
import com.blackboxsociety.http.Ok
import com.blackboxsociety.http.Missing
import scala.Some

trait BlackBox {

  def port: Int

  def host: String
  
  def router: HttpRouter

  def middleware: List[(HttpRequest => Task[HttpResponse]) => (HttpRequest => Task[HttpResponse])] = List()

  lazy val handleRequest = middleware.reverse.foldLeft[HttpRequest => Task[HttpResponse]](route) { (m, n) =>
    n(m)
  }

  final def main(args: Array[String]) {
    run(ImmutableArray.fromArray(args))
  }

  def route(request: HttpRequest): Task[HttpResponse] = router.route(request)

  def genServer(): Task[Unit] = for (
    server <- TcpServer(host, port);
    _      <- Concurrency.forkForever(server.accept() >>= handleConnection)
  ) yield ()

  def servePublicFile(request: HttpRequest): Option[HttpResponse] = {
    if(request.resource.path.startsWith("/assets/")) {
      val src = "target/resource_managed/main/public/" + request.resource.path.substring(8)
      val file = new File(src)
      if(file.exists() && !src.contains("..")) {
        Some(Ok(scala.io.Source.fromFile(file).mkString))
      } else {
        None
      }
    } else {
      None
    }
  }

  def handleConnection(client: TcpClient): Task[Unit] = for (
    response <- parseAndRoute(client).handle(handleError());
    _        <- client.end(HttpResponseConsumer.consume(response))
  ) yield ()

  def handleError(): PartialFunction[Throwable, HttpResponse] = {
    case HttpParserException(e)   =>
      InternalServerError(e)
    case MissingRouteException(r) =>
      servePublicFile(r).getOrElse({ Missing("These are not the droids you're looking for :-/") })
  }

  def parseAndRoute(client: TcpClient): Task[HttpResponse] = for (
    request  <- HttpParser(client);
    response <- handleRequest(request)
  ) yield response

  def run(args: ImmutableArray[String]) = {
    genServer.runAsync({ _ => Unit})
    EventLoop.run()
  }

}

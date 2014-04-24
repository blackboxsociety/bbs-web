package com.blackboxsociety.app

import com.blackboxsociety.services._
import com.blackboxsociety.util._
import scalaz.ImmutableArray
import scalaz.concurrent._
import scalaz.concurrent.Task._
import scalaz.syntax.bind._
import com.blackboxsociety.net._
import com.blackboxsociety.http._
import com.blackboxsociety.app.services._
import com.blackboxsociety.util.parser.TcpParserStream._
import java.io._

object Main {

  def parseAndRoute(services: ServiceManifest, client: TcpClient): Task[HttpResponse] = for (
    request  <- HttpParser(client);
    response <- services.router.route(request)
  ) yield response

  def servePublicFile(request: HttpRequest)(implicit s: ServiceManifest): Option[HttpResponse] = {
    if(request.resource.path.startsWith("/assets/")) {
      val src = "target/scala-2.10/resource_managed/main/public/" + request.resource.path.substring(8)
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

  def handleError(implicit s: ServiceManifest): PartialFunction[Throwable, HttpResponse] = {
    case HttpParserException(e)   =>
      InternalServerError(e)
    case MissingRouteException(r) =>
      servePublicFile(r).getOrElse({ Missing("These are not the droids you're looking for :-/") })
  }

  def handleRequest(services: ServiceManifest)(client: TcpClient): Task[Unit] = for (
    response <- parseAndRoute(services, client).handle(handleError(services));
    _        <- client.end(HttpResponseConsumer.consume(response))
  ) yield ()

  def genServer(services: ServiceManifest): Task[Unit] = for (
    server <- TcpServer("0.0.0.0", 3000);
    _      <- Concurrency.forkForever(server.accept() >>= handleRequest(services))
  ) yield ()

  def genServices(): Task[ServiceManifest] = now { DevServices }

  def run(args: ImmutableArray[String]): Task[Unit] =
    genServices() >>= genServer

  final def main(args: Array[String]) {
    run(ImmutableArray.fromArray(args)).runAsync({ _ => Unit})
    EventLoop.run()
  }

}

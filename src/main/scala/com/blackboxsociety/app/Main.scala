package com.blackboxsociety.app

import com.blackboxsociety.services._
import com.blackboxsociety.util._
import scalaz.ImmutableArray
import scalaz.concurrent._
import scalaz.syntax.bind._
import Task._
import com.blackboxsociety.net._
import com.blackboxsociety.http._
import com.blackboxsociety.http.responses._
import com.blackboxsociety.app.services._

object Main {

  def parseAndRoute(services: ServiceManifest, client: TcpClient): Task[HttpResponse] = for (
    request  <- HttpParser(client);
    response <- services.router.route(request)
  ) yield response

  def handleError(s: ServiceManifest): PartialFunction[Throwable, HttpResponse] = {
    case HttpParserException(e) => InternalServerError(e)(s)
  }

  def handleRequest(services: ServiceManifest)(client: TcpClient): Task[Unit] = for (
    response <- parseAndRoute(services, client).handle(handleError(services));
    _        <- client.end(response.toString)
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

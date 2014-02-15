package com.blackboxsociety.app

import com.blackboxsociety.services._
import com.blackboxsociety.util._
import scalaz.{Validation, ImmutableArray}
import scalaz.concurrent._
import scalaz.syntax.bind._
import Future._
import com.blackboxsociety.net._
import com.blackboxsociety.http._
import com.blackboxsociety.http.responses._
import com.blackboxsociety.app.services._

object Main {

  def routeRequest(s: ServiceManifest, r: Validation[String, HttpRequest]): Future[HttpResponse] = {
    r.fold(
     error   => now { Ok("Something went wrong here")(s) },
     request => s.router.route(request)
    )
  }

  def handleRequest(services: ServiceManifest)(client: TcpClient): Future[Unit] = for (
    request  <- HttpParser(client);
    response <- routeRequest(services, request);
    _        <- client.end(response.toString)
  ) yield ()

  def genServer(services: ServiceManifest): Future[Unit] = for (
    server <- TcpServer("0.0.0.0", 3000);
    _      <- Concurrency.forkForever(server.accept() >>= handleRequest(services))
  ) yield ()

  def genServices(): Future[ServiceManifest] = now { DevServices }

  def run(args: ImmutableArray[String]): Future[Unit] =
    genServices() >>= genServer

  final def main(args: Array[String]) {
    run(ImmutableArray.fromArray(args)).runAsync(identity)
    EventLoop.run()
  }

}

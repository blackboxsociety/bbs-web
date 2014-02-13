package com.blackboxsociety.util

import java.util.concurrent.{Future => _, _}
import scalaz._
import scalaz.concurrent._
import effect._
import Future._

object Concurrency {

  val pool: ExecutorService = Executors.newFixedThreadPool(32)

  def forkIO(n: IO[Unit]): IO[Unit] = IO {
    pool.execute(new Runnable {
      def run(): Unit = n.unsafePerformIO()
    })
  }

  def forkIO(n: Future[Unit]): Future[Unit] = now {
    n.runAsync(identity)
  }

  def forkForever(n: Future[Unit]): Future[Unit] = now {
    n.runAsync({ _ =>
      forkForever(n)
    })
  }

}

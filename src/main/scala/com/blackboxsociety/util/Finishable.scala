package com.blackboxsociety.util

import scalaz._

sealed trait Finishable[A]

case class More[A](a: A) extends Finishable[A]
case class Done[A](a: A) extends Finishable[A]

object Finishable {

  implicit val FinishableFunctor = new Functor[Finishable] {
    override def map[A, B](fa: Finishable[A])(f: (A) => B): Finishable[B] = fa match {
      case More(m) => More(f(m))
      case Done(d) => Done(f(d))
    }
  }

  def more[A](a: A): Finishable[A] = More(a)

  def done[A](a: A): Finishable[A] = Done(a)

}

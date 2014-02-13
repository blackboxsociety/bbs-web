package com.blackboxsociety.util.memory

class FreeList[A](f: => A) {

  var stack: List[A] = List()

  def alloc: A = {
    if(stack.isEmpty) {
      f
    } else {
      val n = stack.head
      stack = stack.tail
      n
    }
  }

  def free(a: A): Unit = {
    stack = a :: stack
  }

}
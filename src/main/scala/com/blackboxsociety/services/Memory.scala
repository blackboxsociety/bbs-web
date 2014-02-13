package com.blackboxsociety.services

import com.blackboxsociety.util.memory.FreeList

object Memory {

  val byteArray = new FreeList[Array[Byte]]({ new Array[Byte](8192) })

}

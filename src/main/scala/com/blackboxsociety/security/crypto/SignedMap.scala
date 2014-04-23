package com.blackboxsociety.security.crypto

import play.api.libs.json.Json
import scala.collection.immutable.SortedSet

object SignedMap {

  def sign(secret: String, map: Map[String, String]): String = {
    Signed.sign(secret, normalize(map))
  }

  def verify(secret: String, signed: String): Option[Map[String, String]] = {
    val text = signed.drop(64)
    val json = Json.parse(text).asOpt[Map[String, String]]
    json flatMap { map =>
      val hash      = sign(secret, map)
      val signature = signed.take(64)
      if (hash == signature){
        Some(map)
      } else{
        None
      }
    }
  }

  private def normalize(map: Map[String, String]): String = {
    val sorted = SortedSet.empty[String] ++ map.keys
    sorted.foldLeft("") { (m, n) => s"$m:$n:${map.get(n).get}" }
  }

}

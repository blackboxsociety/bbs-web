package com.blackboxsociety.security.crypto

import javax.crypto.spec.SecretKeySpec
import javax.crypto.Mac

object Hash {

  def hmacSha256(secret: String, in: String): String = {
    val algo = "HmacSHA256"
    val key  = new SecretKeySpec(secret.getBytes, algo)
    val mac  = Mac.getInstance(algo)
    mac.init(key)
    mac.doFinal(in.getBytes).map("%02X" format _).mkString
  }

}
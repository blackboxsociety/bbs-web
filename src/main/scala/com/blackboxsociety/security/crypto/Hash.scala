package com.blackboxsociety.security.crypto

import javax.crypto.spec.SecretKeySpec
import javax.crypto.Mac
import java.security.MessageDigest

object Hash {

  def hmacSha256(secret: String, in: String): String = {
    val algo = "HmacSHA256"
    val key  = new SecretKeySpec(secret.getBytes, algo)
    val mac  = Mac.getInstance(algo)
    mac.init(key)
    mac.doFinal(in.getBytes).map("%02X" format _).mkString
  }

  def sha1(bytes: Array[Byte]): String = {
    MessageDigest.getInstance("SHA-1").digest(bytes).map("%02X" format _).mkString
  }

}
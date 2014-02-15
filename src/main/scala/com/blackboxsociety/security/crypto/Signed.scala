package com.blackboxsociety.security.crypto

object Signed {

  def sign(secret: String, text: String): String =
    Hash.hmacSha256(secret, text) + text

  def verify(secret: String, signed: String): Option[String] = {
    val text      = signed.drop(64)
    val hash      = Hash.hmacSha256(secret, text)
    val signature = signed.take(64)

    if (hash == signature)
      Some(text)
    else
      None
  }

}
package com.blackboxsociety.http

import play.api.libs.json.Json
import com.blackboxsociety.security.crypto.SignedMap

sealed trait Session[Self <: Session[Self]] {

  val secret: String

  val data: Map[String, String]

  def withFreshData(newData: Map[String, String]): Self

  def withData(newData: Map[String, String]): Self = {
    withFreshData(data ++ newData)
  }

  def set(key: String, value: String): Self = {
    withData(Map(key -> value))
  }

  def get(key: String): Option[String] = {
    data.get(key)
  }

  def clear(): Self = {
    withFreshData(Map())
  }

  def toJson: String = {
    Json.toJson(data).toString()
  }

  def signature(): String = {
    SignedMap.sign(secret, data)
  }

}

case class SignedSession(secret: String, data: Map[String, String]) extends Session[SignedSession] {

  override def withFreshData(newData: Map[String, String]): SignedSession = {
    SignedSession(secret, newData)
  }

}

case class FlashSession(secret: String, data: Map[String, String]) extends Session[FlashSession] {

  override def withFreshData(newData: Map[String, String]): FlashSession = {
    FlashSession(secret, newData)
  }

}
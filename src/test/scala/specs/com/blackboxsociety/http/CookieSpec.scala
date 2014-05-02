package specs.com.blackboxsociety.http

import org.specs2.mutable._
import com.blackboxsociety.http._

class CookieSpec extends Specification {

  val cookieString = "someKey=someValue; anotherKey=anotherValue; beep=bop"

  val cookieMap = Map("someKey" -> "someValue", "anotherKey" -> "anotherValue", "beep" -> "bop")

  "The cookie string \"\"someKey=someValue; anotherKey=anotherValue; beep=bop\"\"" should {
    "create the corresponding Map[String, String]" in {
      Cookie.parse(cookieString) must beEqualTo(cookieMap)
    }
  }
  "The map Map(\"someKey\" -> \"someValue\", \"anotherKey\" -> \"anotherValue\", \"beep\" -> \"bop\")" should {
    "create the string \"someKey=someValue; anotherKey=anotherValue; beep=bop\"" in {
      Cookie.serialize(cookieMap) must beEqualTo(cookieString)
    }
  }

}
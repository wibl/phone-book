package models

import scala.util.matching.Regex
import play.api.libs.json._

class PhoneNumber private (val underlying: String) extends AnyVal {
    override def toString(): String = underlying.toString()
}

object PhoneNumber {
    
    def apply(number: String): PhoneNumber = {
        require(number != null)
        require(verifyFormatNumber(number))
        new PhoneNumber(number)
    }

    def verifyFormatNumber(number: String): Boolean = {
        val pattern: Regex = "^\\+[7]\\d{10}$".r
        pattern.matches(number)
    }

    implicit val numberReads: Reads[PhoneNumber] = {
        ((JsPath).read[String]).map(PhoneNumber(_))
    }
}
package models

class PhoneNumber private (val underlying: String) extends AnyVal {
    override def toString(): String = underlying.toString()
}

object PhoneNumber {
    
    def apply(number: String): PhoneNumber = {
        require(number != null)
        require(verifyFormatNumber(number))
        require(verifyUniqueNumber(number))
        new PhoneNumber(number)
    }

    def verifyFormatNumber(number: String): Boolean = {
        true
    }

    def verifyUniqueNumber(number: String): Boolean = {
        true
    }
}
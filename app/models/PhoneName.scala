package models

class PhoneName private (val underlying: String) extends AnyVal {
    override def toString(): String = underlying.toString()
}

object PhoneName {
    
    def apply(name: String): PhoneName = {
        require(name != null)
        require(verifyFormatName(name))
        new PhoneName(name)
    }

    def verifyFormatName(name: String): Boolean = {
        true
    }
}
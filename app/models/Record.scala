package models

import play.api.libs.json._

case class Record(id: Long, name: PhoneName, number: PhoneNumber)

object Record {
    
    implicit val recordWrites = new Writes[Record] {
        def writes(record: Record): JsValue = {
            Json.obj(
                "id" -> record.id,
                "name" -> record.name.toString(),
                "number" -> record.number.toString()
            )
        }
    }
}
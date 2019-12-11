package models

import play.api.libs.json._
import play.api.libs.functional.syntax._

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

    implicit val recordReads: Reads[Record] = (
        (JsPath \ "id").read[Long] and
        (JsPath \ "name").read[PhoneName] and
        (JsPath \ "number").read[PhoneNumber]
    )(Record.apply _)
}
package models

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Record(id: Long, name: String, number: String)

object Records {

    private val records = List(
        Record(1, "Ivanov", "+71111111111"),
        Record(2, "Petrov", "+72222222222"),
        Record(1, "Sidorov", "+73333333333")
    )

    def getAllRecords: Future[List[Record]] = {
        Future { 
            records
        }
    }
}
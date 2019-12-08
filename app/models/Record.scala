package models

import javax.inject.Inject
import scala.concurrent.{ Future, ExecutionContext }
import dao.Storage

case class Record(id: Long, name: PhoneName, number: PhoneNumber)

class Records @Inject() (storage: Storage) {
    
    // private val records = List(
    //     Record(1, PhoneName("Ivanov"), PhoneNumber("+71111111111")),
    //     Record(2, PhoneName("Petrov"), PhoneNumber("+72222222222")),
    //     Record(3, PhoneName("Sidorov"), PhoneNumber("+73333333333"))
    // )

    def getAllRecords: Future[Seq[Record]] = {
        storage.getAllRecords
        //storage.getAllRecords.map(_.map(r => Test(r(0))))
    }

    def addNewRecord(name: PhoneName, number: PhoneNumber) = {
        //records.add
    }
}
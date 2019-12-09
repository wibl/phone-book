package services

import javax.inject.Inject
import scala.concurrent.Future
import models.Record
import dao.Storage

class RecordService @Inject() (storage: Storage) {

    def getAllRecords: Future[Seq[Record]] = {
        storage.getAllRecords
    }

    def addNewRecord(record: Record) = {
        storage.addNewRecord(record)
    }
}
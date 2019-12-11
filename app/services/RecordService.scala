package services

import javax.inject.Inject
import scala.concurrent.Future
import models.Record
import dao.Storage
import scala.concurrent.ExecutionContext.Implicits.global

class RecordService @Inject() (storage: Storage) {

    def getAllRecords: Future[Seq[Record]] = {
        storage.getAllRecords
    }

    def addNewRecord(record: Record): Future[Option[Record]] = {
        storage.findRecord(record).flatMap {
            case Some(r) => Future.successful(None)
            case None => storage.addNewRecord(record).map {
                r => Some(r)
            }
        }
    }

    def updateRecord(record: Record): Future[Option[Record]] = {
        storage.updateRecord(record)
        storage.getById(record.id)
    }

    def deleteRecord(id: Long) = {
        storage.deleteRecord(id)
    }

    def getAllByName(name: String): Future[Seq[Record]] = {
        storage.getAllByName(name)
    }

    def getAllByNumber(number: String): Future[Seq[Record]] = {
        storage.getAllByNumber(number)
    }
}
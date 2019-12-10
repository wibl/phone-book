package services

import javax.inject.Inject
import scala.concurrent.Future
import models.Record
import dao.Storage

class RecordService @Inject() (storage: Storage) {

    def getAllRecords: Future[Seq[Record]] = {
        storage.getAllRecords
    }

    def addNewRecord(name: String, number:String) = {
        storage.addNewRecord(name, number)
    }

    def updateName(id: Long, name: String) = {
        storage.updateName(id, name)
    }

    def updateNumber(id: Long, number: String) = {
        storage.updateNumber(id, number)
    }

    def deleteRecord(id: Long) = {
        storage.deleteRecord(id)
    }

    def getAllByName(name: String) = {
        storage.getAllByName(name)
    }

    def getAllByNumber(number: String) = {
        storage.getAllByNumber(number)
    }
}
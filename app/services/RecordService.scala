package services

import javax.inject.Inject
import scala.concurrent.Future
import models.{Record, Records}

class RecordService @Inject() (records: Records) {

    def getAllRecords: Future[Seq[Record]] = {
        records.getAllRecords
    }
}
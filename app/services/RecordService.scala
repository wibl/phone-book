package services

import scala.concurrent.Future
import models.{Record, Records}

object RecordService {

    def getAllRecords: Future[List[Record]] = {
        Records.getAllRecords
    }
}
package models

import javax.inject.Inject
import scala.concurrent.{ Future, ExecutionContext }
import dao.Storage

case class Record(id: Long, name: PhoneName, number: PhoneNumber)
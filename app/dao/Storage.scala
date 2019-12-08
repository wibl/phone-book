package dao

import scala.concurrent.{ ExecutionContext, Future }
import javax.inject.Inject
import models.{ Record, PhoneName, PhoneNumber }
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import slick.jdbc.JdbcProfile

class Storage @Inject() 
    (protected val dbConfigProvider: DatabaseConfigProvider)
    (implicit executionContext: ExecutionContext) 
    extends HasDatabaseConfigProvider[JdbcProfile] {

    import profile.api._

    private class RecordTable(tag: Tag) extends Table[Record](tag, "RECORD") {
        
        def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
        def name = column[String]("NAME")
        def number = column[String]("PHONE")

        //def * = (id, name, number) <> (Record.tupled, Record.unapply)
        //def * = (id, name, number) <> ((Record.apply _).tupled, Record.unapply)
        // def * = id ~ name ~ number <> (
        //     (id, name, number) => Record(id, PhoneName(name), PhoneNumber(number)),
        //     (r: Record) => (r.id, r.name.toString, r.number.toString))
        override def * = (id, name, number) <> (apply, unapply)
        def apply(t: (Long, String, String)): Record = Record(t._1, PhoneName(t._2), PhoneNumber(t._3))
        def unapply(r: Record): Option[(Long, String, String)] = Some((r.id, r.name.toString, r.number.toString))    
    }

    private val records = TableQuery[RecordTable]

    def getAllRecords: Future[Seq[(Record)]] = db.run(records.result)

    def addNewRecord(name: String, number: String) = {
        db.run(records.map(r => (r.name, r.number)) += (name, number))
    }
}
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

    private class RecordTable(tag: Tag) extends Table[Record](tag, "records") {
        
        def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
        def name = column[String]("name")
        def number = column[String]("number")

        override def * = (id, name, number) <> (apply, unapply)
        def apply(t: (Long, String, String)): Record = Record(t._1, PhoneName(t._2), PhoneNumber(t._3))
        def unapply(r: Record): Option[(Long, String, String)] = Some((r.id, r.name.toString, r.number.toString))    
    }

    private val records = TableQuery[RecordTable]

    def getAllRecords: Future[Seq[Record]] = {
        val q = records.sortBy(r => (r.id))
        db.run(q.result)
    }

    def addNewRecord(record: Record): Future[Record] = {
        val q = (records returning records) += record
        db.run(q)
    }

    def updateRecord(record: Record) = {
        val q = records.filter(_.id === record.id).map(r => (r.name, r.number))
        db.run(q.update(record.name.toString(), record.number.toString()))
    }

    def deleteRecord(id: Long) = {
        val q = records.filter(_.id === id)
        db.run(q.delete)
    }

    def getAllByName(name: String): Future[Seq[Record]] = {
        val q = records.filter(_.name === name).sortBy(r => (r.id))
        db.run(q.result)
    }

    def getAllByNumber(number: String): Future[Seq[Record]] = {
        val q = records.filter(_.number === number).sortBy(r => (r.id))
        db.run(q.result)
    }

    def findRecord(record: Record): Future[Option[Record]] = {
        val q = records
            .filter(_.name === record.name.toString())
            .filter(_.number === record.number.toString())
        db.run(q.result.headOption)
    }

    def getById(id: Long): Future[Option[Record]] = {
        val q = records.filter(_.id === id)
        db.run(q.result.headOption)
    }
}
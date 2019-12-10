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

    def getAllRecords: Future[Seq[Record]] = db.run(records.result)

    def addNewRecord(name: String, number:String) = {
        db.run(records.map(r => (r.name, r.number)) += (name, number))
    }

    def updateName(id: Long, name: String) = {
        val q = for { r <- records if r.id === id } yield r.name
        db.run(q.update(name))
    }

    def updateNumber(id: Long, number: String) = {
        val q = for { r <- records if r.id === id } yield r.number
        db.run(q.update(number))
    }

    def deleteRecord(id: Long) = {
        val q = records.filter(_.id === id)
        db.run(q.delete)
    }

    def getAllByName(name: String): Future[Seq[Record]] = {
        val q = records.filter(_.name === name)
        db.run(q.result)
    }

    def getAllByNumber(number: String): Future[Seq[Record]] = {
        val q = records.filter(_.number === number)
        db.run(q.result)
    }
}
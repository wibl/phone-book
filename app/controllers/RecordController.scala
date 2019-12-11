package controllers

import javax.inject.Inject
import play.api.mvc._
import services.RecordService
import scala.concurrent.ExecutionContext.Implicits.global
import models.{Record, PhoneName, PhoneNumber}
import play.api.libs.json._
import views.html.defaultpages.error
import scala.concurrent.Future

class RecordController @Inject() (recordService: RecordService, val controllerComponents: ControllerComponents) extends BaseController {

  def list = Action.async { implicit request =>
    recordService.getAllRecords.map(record => Ok(Json.toJson(record)))
  }

  def insert = Action.async(parse.json) { implicit request =>
    unmarshalJsValue(request) { record: Record =>
      recordService.addNewRecord(record).map {
        case None => Conflict
        case Some(r) => Created(Json.toJson(r))
      }
    }
  }

  def updateRecord = Action.async(parse.json) { implicit request =>
    unmarshalJsValue(request) { record: Record =>
      recordService.updateRecord(record).map(
        r => Ok(Json.toJson(r))
      )
    }
  }

  def deleteRecord(id: Long) = Action.async { implicit request =>
    recordService.deleteRecord(id)
    Future.successful(NoContent)    
  }

  def getAllByName(name: String) = Action.async { implicit request =>
    recordService.getAllByName(name).map(record => Ok(Json.toJson(record)))
  }

  def getAllByNumber(number: String) = Action.async { implicit request =>
    recordService.getAllByNumber(number).map(record => Ok(Json.toJson(record)))
  }
  
  def unmarshalJsValue[R](request: Request[JsValue])(block: R => Future[Result])(implicit rds : Reads[R]): Future[Result] = {
    request.body.validate[R](rds).fold(
      valid = block,
      invalid = e => {
        Future.successful(BadRequest("JsonError - " + e.toString()))
      }
    )
  }
}

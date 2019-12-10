package controllers

import javax.inject.Inject
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.RecordService
import scala.concurrent.ExecutionContext.Implicits.global
import models.{Record, PhoneName, PhoneNumber}
import play.api.libs.json.Json

class RecordController @Inject()(recordService: RecordService, val controllerComponents: ControllerComponents) extends BaseController {

  def list = Action.async { implicit request =>
    recordService.getAllRecords.map(record => Ok(Json.toJson(record)))
  }

  def insert(name: String, number: String) = Action.async { implicit request =>
    recordService.addNewRecord(name, number).map(
      result => Redirect(routes.RecordController.list())
    )
  }

  def updateName(id: Long, name: String) = Action.async { implicit request =>
    recordService.updateName(id, name).map(
      result => Redirect(routes.RecordController.list())
    )
  }

  def updateNumber(id: Long, number: String) = Action.async { implicit request =>
    recordService.updateNumber(id, number).map(
      result => Redirect(routes.RecordController.list())
    )
  }

  def deleteRecord(id: Long) = Action.async { implicit request =>
    recordService.deleteRecord(id).map(
      result => Redirect(routes.RecordController.list())
    )
  }

  def getAllByName(name: String) = Action.async { implicit request =>
    recordService.getAllByName(name).map(record => Ok(Json.toJson(record)))
  }

  def getAllByNumber(number: String) = Action.async { implicit request =>
    recordService.getAllByNumber(number).map(record => Ok(Json.toJson(record)))
  }
}

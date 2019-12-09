package controllers

import javax.inject.Inject
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.RecordService
import scala.concurrent.ExecutionContext.Implicits.global
import models.{Record, PhoneName, PhoneNumber}

class RecordController @Inject()(recordService: RecordService, val controllerComponents: ControllerComponents) extends BaseController {

  def list = Action.async { implicit request =>
    recordService.getAllRecords.map(record => Ok("Result - " + record))
  }

  def insert(name: String, number: String) = Action.async { implicit request =>
    recordService.addNewRecord(Record(1, PhoneName(name), PhoneNumber(number))).map(
      result => Redirect(routes.RecordController.list())
    )
  }
}

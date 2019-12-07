package controllers

import javax.inject.Inject
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}
import services.RecordService
import scala.concurrent.ExecutionContext.Implicits.global

class RecordController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def list = Action.async { implicit request =>
    RecordService.getAllRecords.map(record => Ok("Result - " + record))
  }
}

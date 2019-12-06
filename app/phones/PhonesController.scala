package phones

import play.api.mvc.{Action, AnyContent}

class PhonesController {
  def create(number: Int, name: String): Action[AnyContent] = {}
  def list: Action[AnyContent] = {}
}

package phones

import javax.inject.Inject
import play.api.routing.Router.Routes
import play.api.routing.SimpleRouter


class PhonesRouter @Inject()(controller: PhonesController) extends SimpleRouter {
  override def routes: Routes = {
    case POST (p"/$number/$name") =>
      controller.create(number, name)
    case GET (p"/") =>
      controller.list
  }
}

package controllers

import play.api.Routes
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.EssentialAction
import play.core.Router.JavascriptReverseRoute
import play.core.Router._
import routes.javascript.Application.index
import routes.javascript.Application.ajaxCall

object JavascriptRoute extends Controller {

  val appRoutes: List[JavascriptReverseRoute] = List(index, ajaxCall)

  val javascriptRouters = appRoutes

  def javascriptRoutes: EssentialAction = Action { implicit request =>
    import routes.javascript._
    Ok(Routes.javascriptRouter("jsRoutes")(javascriptRouters: _*)).as("text/javascript")
  }

}
package controllers

import play.api.mvc._
import play.api.routing.JavaScriptReverseRouter


class JavascriptController extends Controller{

  def javascriptRoutes = Action { implicit request =>
    Ok(
      JavaScriptReverseRouter("jsRoutes")(
        routes.javascript.HomeController.index,
        routes.javascript.CountController.count,
        routes.javascript.SignInController.signIn,
        routes.javascript.SignInController.signInProcess,
        routes.javascript.SignUpController.signUp,
        routes.javascript.SignUpController.addPersonDetails,
        routes.javascript.ProfileController.profile,
        routes.javascript.ManagementController.manage,
        routes.javascript.LogoutController.logout,
        routes.javascript.ManagementController.toggle
      )
    ).as("text/javascript")
  }

}
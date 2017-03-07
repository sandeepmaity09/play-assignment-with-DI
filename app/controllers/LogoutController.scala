package controllers

import com.google.inject.Inject
import play.api.mvc._

class LogoutController @Inject() extends Controller{

  def logout = Action{ implicit request=>
    Redirect(routes.SignInController.signIn()).withNewSession
  }

}

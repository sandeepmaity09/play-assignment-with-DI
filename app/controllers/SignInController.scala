package controllers

import Models._
import com.google.inject.Inject
import play.api.mvc._
import services.PersonService

class SignInController @Inject()(person: PersonService) extends Controller {

  def signIn = Action { implicit request =>
    Ok(views.html.signinpage())
  }

  def signInProcess = Action { implicit request =>
    LogingDetailsMapping.LogingDetailsForm.bindFromRequest.fold(
      formWithErrors => {
        Redirect(routes.SignInController.signIn()).flashing(
          "error" -> "Something went wrong Please Try Again Later"
        )
      },
      personData => {
        val personD = person.getPerson(personData.username)
        //        println(personD)
                println(personData)
        if (personD == null) {
          Redirect(routes.SignInController.signIn()).flashing("error" -> "User Doesn't Exist")
        }

        if (person.checkPerson(personData.username, personData.passwd)) {
          if (personD.isEnabled) {
            Redirect(routes.ProfileController.profile()).withSession(
              "username" -> personData.username
            )
          }
          else {
            Redirect(routes.SignInController.signIn()).flashing(
              "error" -> "Account Disabled. Please Contact Your Admininstrator"
            )
          }
        }
        else Redirect(routes.SignInController.signIn()).flashing(
          "error" -> "Username Password Didn't Match"
        )
      }
    )
  }


}

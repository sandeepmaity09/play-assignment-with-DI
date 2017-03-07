package controllers

import com.google.inject.Inject
import models.PersonDetailsMapping
import play.api.mvc._
import play.api.cache._
import services.PersonService

class SignUpController @Inject() (cache:CacheApi,person:PersonService)extends Controller {

  def signUp = Action {
    Ok(views.html.signuppage())
  }

  def addPersonDetails = Action { implicit request =>
    PersonDetailsMapping.personDetailsForm.bindFromRequest.fold(
      formWithErrors => {
        Redirect(routes.SignUpController.signUp()).flashing(
          "error" -> "Something went wrong"
        )
      },
      personData => {
        //        person.addPerson(personData)
//        cache.set(personData.username, personData)
        person.addPerson(personData)
        Redirect(routes.ProfileController.profile()).withSession(
          "username" -> personData.username
        )
      }
    )
  }
}

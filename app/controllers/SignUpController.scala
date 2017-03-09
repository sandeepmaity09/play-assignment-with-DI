package controllers

import com.google.inject.Inject
import models.PersonDetailsMapping
import play.api.Configuration
import play.api.mvc._
import play.api.cache._
import services.PersonService

class SignUpController @Inject() (cache:CacheApi,person:PersonService,config:Configuration)extends Controller {

  def signUp = Action { implicit request =>
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

//        val persons = person.getPersonList()
//        println(persons)
        val flag = person.checkAddPerson(personData.username)
//        println(flag)
//        println(persons)

        if(!flag){
//          println(person.getPersonList())
          person.addPerson(personData)
//          println(person.getPersonList())
          Redirect(routes.ProfileController.profile()).withSession(
            "username" -> personData.username
          )
        }
        else {
//          println(person.getPersonList())
          Redirect(routes.SignUpController.signUp()).flashing(
            "errors" -> "User already exist"
          )
        }

      }
    )
  }
}

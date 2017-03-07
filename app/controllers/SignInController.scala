package controllers

import Models._
import com.google.inject.Inject
import play.api.cache.CacheApi
import play.api.mvc._
import services.PersonService

class SignInController @Inject()(person:PersonService,cache:CacheApi)extends Controller {

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
        /*        val flag: Boolean = check(person)
        if (flag)
          Redirect(routes.ProfileController.profile()).withSession(
            "username" -> person.username
          )
        else {
          Redirect(routes.SignInController.signIn()).flashing(
            "error" -> "Username and password wrong"
          )
        }*/

        /*        val demoUser: Option[PersonDetails] = cache.get[PersonDetails](personData.username)

        demoUser match {
          case Some(result) if(personData.username.equals((result.username))) => {Redirect(routes.ProfileController.profile()).withSession(("username" -> result.username))}
          case _ => {Redirect(routes.SignInController.signIn()).flashing("error" -> "username & password doesn't match")}
        }*/
        person.getPerson(personData.username)
        Redirect(routes.ProfileController.profile()).withSession("username" -> personData.username)
      }
    )
  }

//
//  def check(person:LogingDetails):Boolean={
//    val listpwdnotmatch = person.personList.map(
//      value=>(value.username,value.passwd)
//    )
//    if(listpwdnotmatch.contains((person.username,person.passwd)))
//      true
//    else
//      false
//  }


}

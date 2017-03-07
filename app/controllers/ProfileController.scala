package controllers

import Models.PersonDetails
import com.google.inject.Inject
import play.api.mvc._
import play.api.cache._
import services.PersonService

class ProfileController @Inject()(cache:CacheApi,person:PersonService)  extends Controller {

  def profile = Action { implicit request =>
    request.session.get("username").map { username =>
//      val personData = person.getPerson(username)
      val demoPerson:Option[PersonDetails] = cache.get[PersonDetails](username)
      demoPerson match {
        case Some(result) if (username.equals(result.username))=>Ok(views.html.profile(result))
        case None => Ok("There is no user")
      }
//      Ok(views.html.profile(demoPerson))
    }.getOrElse {
      Unauthorized("Something went wrong")
    }
  }
}

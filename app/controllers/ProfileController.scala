package controllers

import Models.LogingDetailsMapping
import com.google.inject.Inject
import play.api.mvc._
import play.api.cache._
import services.PersonService

class ProfileController @Inject()(cache:CacheApi,person:PersonService)  extends Controller {

  def profile = Action { implicit request =>
    request.session.get("username").map { username =>
      Ok(views.html.profile(person.getPerson(username)))
    }.getOrElse {
      Unauthorized("Something went wrong")
    }
  }
}


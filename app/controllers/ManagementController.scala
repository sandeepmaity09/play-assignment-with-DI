package controllers

import Models.ToggleMapping
import play.api.mvc._
import com.google.inject.Inject
import models.PersonDetailsMapping
import play.api.cache.CacheApi
import services.PersonServiceCacheImpl

class ManagementController @Inject()(cache:CacheApi,person:PersonServiceCacheImpl) extends Controller{

  def manage() = Action{ implicit request =>
  request.session.get("username").map{ session =>
  Ok(views.html.management(person.getPersonList(),person.getPerson(session)))
  }.getOrElse{
    Unauthorized("Oops, you are not logged in")
  }
  }

  def toggle() = Action { implicit request =>
    request.session.get("username").map{ session =>
//      val username = PersonDetailsMapping.personDetailsForm.bindFromRequest.get
      println("1")
      val tempPerson = ToggleMapping.toggleForm.bindFromRequest.get
//      person.toggleEnabler(username.username)
//      val personLists = person.getPersonList()
      println("2")
      println(person.getPersonList())
        person.toggleEnabler(tempPerson)
      println("3")
//      person.toggleEnabler(username)
      println(person.getPersonList())
      Ok(views.html.profile(person.getPerson(session)))
    }.getOrElse{
      Unauthorized("Oops,you are not connected")
    }
  }
}

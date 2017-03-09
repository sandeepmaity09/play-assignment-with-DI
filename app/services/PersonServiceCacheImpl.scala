package services

import Models.PersonDetails
import com.google.inject.Inject
import play.api.Configuration
import play.api.cache.CacheApi

import scala.collection.mutable.ListBuffer

class PersonServiceCacheImpl @Inject()(cache: CacheApi,config:Configuration) extends PersonService {

  cache.set("personList", ListBuffer[PersonDetails]())

  override def addPerson(person: PersonDetails): Boolean = {
    try {
      val encPrson = person.copy(passwd = encryption.encrypt(person.passwd))
      val encPerson = adminChecker(encPrson)

      val auth:Option[String] = config.getString("role")
      val authL:Boolean = auth match {
        case Some(result) if (result == "admin") => true
        case _ => false
      }
//      val personList: ListBuffer[PersonDetails] = cache.get[ListBuffer[PersonDetails]]("personList").get
      val personL:Option[ListBuffer[PersonDetails]] = cache.get[ListBuffer[PersonDetails]]("personList")
      val personList = personL match {
        case Some(result) => result
        case _ => ListBuffer[PersonDetails]()
      }
      cache.set("personList", personList += encPerson)
      true
    } catch {
      case e: Exception => false
    }
  }

  override def getPerson(username: String): PersonDetails = {
    //    val personList:ListBuffer[PersonDetails] = cache.get[ListBuffer[PersonDetails]]("personList").get
    val personL: Option[ListBuffer[PersonDetails]] = cache.get[ListBuffer[PersonDetails]]("personList")

    val personList: ListBuffer[PersonDetails] = personL match {
      case Some(result) => result
      case _ => new ListBuffer[PersonDetails]()
    }
    println(personList)

    if(personList != ListBuffer[PersonDetails]()) {
      return personList.filter(person => (person.username == username))(0)
    }
    else{
      null
    }

  }

  override def getPersonList(): ListBuffer[PersonDetails] = {
    //    cache.get[ListBuffer[PersonDetails]]("personList").get
    val personL = cache.get[ListBuffer[PersonDetails]]("personList")
    personL match {
      case Some(result) => result
      case _ => ListBuffer[PersonDetails]()
    }
  }

  override def checkPerson(username: String, passwd: String): Boolean = {
    //    val personList:ListBuffer[PersonDetails] = cache.get[ListBuffer[PersonDetails]]("personList").get
    val personL: Option[ListBuffer[PersonDetails]] = cache.get[ListBuffer[PersonDetails]]("personList")
    val personList = personL match {
      case Some(result) => result
      case _ => ListBuffer[PersonDetails]()
    }
    personList.filter(person => (person.username == username) && (person.passwd == encryption.encrypt(passwd))).length == 1
  }

  override def checkAddPerson(username: String): Boolean = {
    //    val personList:ListBuffer[PersonDetails] = cache.get[ListBuffer[PersonDetails]]("personList").get
    val personL: Option[ListBuffer[PersonDetails]] = cache.get[ListBuffer[PersonDetails]]("personList")
    val personList = personL match {
      case Some(result) => result
      case _ => ListBuffer[PersonDetails]()
    }
    personList.filter(person => (person.username == username)).length == 1
  }

  private def adminChecker(prson:PersonDetails):PersonDetails = {
    val auth:Option[String] = config.getString("role")
    auth match {
      case Some(result) if(result == "admin") => personEnabler(prson.copy(isAdmin = true))
      case None => personEnabler(prson)
    }
  }

  private def personEnabler(prson:PersonDetails):PersonDetails = {
    if(prson.isAdmin)
      prson.copy(isEnabled = true)
    else
      prson.copy(isEnabled = true)
  }

  def toggleEnabler(personname:String):Boolean = {
    val personList:ListBuffer[PersonDetails] = cache.get[ListBuffer[PersonDetails]]("personList").getOrElse(ListBuffer[PersonDetails]())
    val tempUser = getPerson(personname)
    val newUser = tempUser.copy(isEnabled = !tempUser.isEnabled)
    cache.set("personList",(personList-tempUser)+=newUser)
    true
  }

  /*  override def addPersonChecker(person):Boolean = {
      val personList:ListBuffer[PersonDetails] =cache.get[ListBuffer[PersonDetails]]("personList").get

    }*/

}

package services

import Models.PersonDetails
import com.google.inject.Inject
import play.api.cache.CacheApi

import scala.collection.mutable.ListBuffer

class PersonServiceCacheImpl @Inject()(cache:CacheApi) extends PersonService{

  cache.set("personList",ListBuffer[PersonDetails]())

  override def addPerson(person: PersonDetails) = {
    try{
      val encPerson = person.copy(passwd = encryption.encrypt(person.passwd))
      val personList:ListBuffer[PersonDetails] = cache.get[ListBuffer[PersonDetails]]("personList").get

      cache.set("personList",personList+=encPerson)
      true
    }catch{
      case e:Exception => false
    }
  }

  override def getPerson(username: String):PersonDetails = {

    val personList:ListBuffer[PersonDetails] = cache.get[ListBuffer[PersonDetails]]("personList").get
    personList.filter(person => (person.username == username))(0)
  }

  override def getPersonList():ListBuffer[PersonDetails] = {
    cache.get[ListBuffer[PersonDetails]]("personList").get
  }

}

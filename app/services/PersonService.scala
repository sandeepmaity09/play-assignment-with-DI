package services

import Models.PersonDetails
import com.google.inject.Singleton

import scala.collection.mutable.ListBuffer

/*@Singleton
class PersonService {

  val personList:ListBuffer[PersonDetails] = ListBuffer[PersonDetails]()

  def addPerson(person:PersonDetails) = personList+=person
  def getPersonList = personList
  def getPerson(username:String):PersonDetails={
    val user=personList.filter(_.username==username)
    user.head
  }

}*/

trait PersonService {
  def addPerson(person:PersonDetails):Boolean
  def getPersonList():ListBuffer[PersonDetails]
  def getPerson(username:String):PersonDetails
}


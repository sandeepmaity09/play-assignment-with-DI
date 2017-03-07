package Models

import play.api.libs.json.Json

case class PersonDetails(fName:String, mName:String, lName:String, email:String,username:String,
                         passwd:String, mobile:String, gender:String, age:Int, hobbies:String)

object PersonDetails {
  implicit val personDetailsFormat = Json.format[PersonDetails]
}
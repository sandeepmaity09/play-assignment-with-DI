package models

import Models.PersonDetails

import play.api.data.Form
import play.api.data.Forms._

object PersonDetailsMapping {

  val personDetailsForm : Form[PersonDetails] = Form {
    mapping(
      "fName" -> nonEmptyText,
      "mName" -> text,
      "lName" -> nonEmptyText,
      "email" -> nonEmptyText,
      "username" -> nonEmptyText,
      "passwd" -> nonEmptyText(minLength = 6),
      "mobile" -> nonEmptyText(minLength = 10,maxLength = 10),
      "gender" -> nonEmptyText,
      "age" -> number(min=18,max=75),
      "hobbies" -> text,
      "isAdmin" -> boolean,
      "isEnabled" -> boolean
    )(PersonDetails.apply)(PersonDetails.unapply)
  }
}
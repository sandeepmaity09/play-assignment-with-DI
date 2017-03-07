package Models

import play.api.data.Form
import play.api.data.Forms._

object LogingDetailsMapping {

  val LogingDetailsForm:Form[LogingDetails] = Form {
    mapping(
      "username" -> text,
      "passwd" -> text
    )(LogingDetails.apply)(LogingDetails.unapply)
  }

}

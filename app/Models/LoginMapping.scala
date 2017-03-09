package Models

import play.api.data.Form
import play.api.data.Forms._

object LogingDetailsMapping {

  val LogingDetailsForm:Form[LogingDetails] = Form {
    mapping(
      "username" -> nonEmptyText,
      "passwd" -> nonEmptyText
    )(LogingDetails.apply)(LogingDetails.unapply)
  }

}

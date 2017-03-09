package Models

import play.api.data.Form
import play.api.data.Forms._

object ToggleMapping {
  val toggleForm = Form(
    single(
      "personname" -> text
    )
  )
}
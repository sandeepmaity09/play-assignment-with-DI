package Models

import play.api.libs.json.Json

case class LogingDetails(username:String,passwd:String)

object LogingDetails {
  implicit val LogingDetailsFormat = Json.format[LogingDetails]
}
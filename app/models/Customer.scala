package models

import play.api.libs.json.Json
import play.api.libs.json.OFormat

case class Customer(id: Int, name: String, age:Int)

object Customer{
  implicit val format: OFormat[Customer] = Json.format[Customer]
}



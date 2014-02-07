package models.database

import play.api.db.slick.Config.driver.simple._
import scala.Long
import scala.Predef._

case class User(id:Option[Long], email:String)

class Users extends Table[User]("User") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def email = column[String]("EMAIL")
  def * = id.? ~ email <> (User, User.unapply _)
  val byId = createFinderBy(_.id)
  val byEmail = createFinderBy(_.email)
  def autoInc = * returning id
}
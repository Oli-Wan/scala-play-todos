package models.database

import play.api.libs.json._
import play.api.db.slick.Config.driver.simple._
import play.api.libs.json.JsNumber
import java.math.BigDecimal

case class Task(id: Option[Long], label: String, completed: Boolean, ownerId: Long = 0)

class Tasks extends Table[Task]("Task") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def label = column[String]("LABEL")
  def completed = column[Boolean]("COMPLETED")
  def ownerId = column[Long]("OWNER_ID")
  def owner = foreignKey("OWNER", ownerId, new Users)(_.id)

  def * = id.? ~ label ~ completed ~ ownerId <> (Task, Task.unapply _)
  def autoInc = * returning id
  val byId = createFinderBy(_.id)
}
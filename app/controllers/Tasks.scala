package controllers

import utils.AuthenticationWrapper
import models.components.TaskRepositoryComponent
import models.slick.TaskSlickRepository
import models.database.Task
import scala.Some
import play.api.libs.json._
import play.api.libs.functional.syntax._

trait Tasks extends AuthenticationWrapper with TaskRepositoryComponent {

  implicit val formats = (
      (__ \ "id").formatNullable[Long] and
      (__ \ "label").format[String] and
      (__ \ "completed").format[Boolean] and
      (__ \ "ownerId").format[Long]
    )(Task.apply, unlift(Task.unapply))

  def index = SecuredAction {
    implicit request => Ok(views.html.index())
  }

  def tasks = SecuredAction {
    implicit request => Ok(Json.toJson(taskRepository.getByEmail(request.session.get("user").getOrElse("jdoe@unknown.org"))))
  }

  def newTask = SecuredAction {
    implicit request =>
      request.body.asJson match {
        case Some(json) =>
          (json \ "label").asOpt[String] match {
            case Some(label) =>
              Ok(Json.toJson(taskRepository.create(label, request.session.get("user").getOrElse("jdoe@unknown.org"))))
            case None =>
              BadRequest("No label found")
          }
        case None => BadRequest("Empty or wrong request")
      }
  }

  def deleteTask(id: Long) = SecuredAction {
    implicit request =>
      taskRepository.delete(id)
      Ok("Task deleted")
  }

  def updateTask(id: Long) = SecuredAction {
    implicit request =>
      request.body.asJson match {
        case Some(json) =>
          json.asOpt[Task] match {
            case Some(task) =>
              taskRepository.update(id, task)
              Ok(Json.toJson(taskRepository.getById(id)))
            case None =>
              BadRequest("Empty or bad request")
          }
        case None => BadRequest("Empty or bad request")
      }
  }
}

object Tasks extends Tasks with TaskSlickRepository
package models.slick


import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._
import models.components.TaskRepositoryComponent
import models.database.{Users, Task, Tasks}

trait TaskSlickRepository extends TaskRepositoryComponent {
  def taskRepository = new SlickRepository

  class SlickRepository extends TaskRepository with UserSlickRepository {
    val tasks = new Tasks
    val users = new Users

    def all(): List[Task] = {
      DB.withSession {
        implicit session: Session =>
          Query(tasks).sortBy(_.id).list
      }
    }

    def create(label: String, email: String): Option[Task] = {
      DB.withSession {
        implicit session: Session =>
          userRepository.getByEmail(email) match  {
            case Some(user) =>
              getById(tasks.autoInc.insert(Task(None, label, completed = false, user.id.getOrElse(0))))
            case None =>
              None
          }
      }
    }

    def update(id: Long, task: Task) = {
      DB.withSession {
        implicit session: Session =>
          Query(tasks)
            .where(_.id === id)
            .map(t => t.label ~ t.completed)
            .update((task.label, task.completed))
      }
    }

    def delete(id: Long)= {
      DB.withSession {
        implicit session: Session =>
          Query(tasks).where(_.id === id).delete
      }
    }

    def getById(id: Long): Option[Task] = {
      DB.withSession {
        implicit session: Session =>
          tasks.byId(id).firstOption
      }
    }

    def getByEmail(email: String): List[Task] = {
      DB.withSession {
        implicit session: Session =>
          (for {
            u <- users if u.email === email
            t <- tasks if t.ownerId === u.id
          } yield t).list
      }
    }
  }
}

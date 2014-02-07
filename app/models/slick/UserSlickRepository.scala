package models.slick

import models.components.UserRepositoryComponent
import models.database.{Users, User}
import play.api.db.slick.DB
import play.api.Play.current
import scala.slick.session.Session

trait UserSlickRepository extends UserRepositoryComponent{
  def userRepository = new SlickRepository

  class SlickRepository extends UserRepository {
    val table = new Users

    def getById(id: Long): Option[User] = {
      DB.withSession {
        implicit session: Session =>
          table.byId(id).firstOption
      }
    }

    def getByEmail(email: String): Option[User] = {
      DB.withSession {
        implicit session: Session =>
          table.byEmail(email).firstOption
      }
    }

    def create(email: String) = {
      DB.withSession {
        implicit session: Session =>
          table.autoInc.insert(User(None, email))
      }
    }
  }
}
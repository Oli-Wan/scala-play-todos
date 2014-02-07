package models.components

import models.database.User


trait UserRepositoryComponent {
  def userRepository: UserRepository

  trait UserRepository {
    def getById(id: Long): Option[User]
    def getByEmail(email: String): Option[User]
    def create(email: String)
  }
}

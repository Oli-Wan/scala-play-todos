package models.components

import models.database.Task


trait TaskRepositoryComponent {
  def taskRepository: TaskRepository

  trait TaskRepository {
    def all(): List[Task]
    def create(label: String, email: String): Option[Task]
    def update(id: Long, task: Task)
    def delete(id: Long)
    def getById(id: Long): Option[Task]
    def getByEmail(email: String): List[Task]
  }
}
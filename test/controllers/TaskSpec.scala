package controllers


import tests.UnitSpec
import play.api.test._
import play.api.test.Helpers._
import models.components.TaskRepositoryComponent
import play.api.libs.json.Json
import scala.Some
import models.database.Task
import models.database.Task

class TaskSpec extends UnitSpec {
  val tasks = Task(Some(1), "blabla", completed = false, 1) :: Task(Some(2), "completed", completed = true, 1) :: Nil

  trait FakeTaskRepo extends TaskRepositoryComponent {
    val fake = mock[FakeRepo]
    def taskRepository = fake

    class FakeRepo extends TaskRepository{

      def all(): List[Task] = tasks

      def create(label: String, email: String): Option[Task] = {
        Some(Task(Some(1), label, completed = false, 1))
      }

      def update(id: Long, task: Task): Unit = {}

      def delete(id: Long): Unit = {}

      def getById(id: Long): Option[Task] = {
        Some(Task(Some(id), "blabla", completed = false, 1))
      }

      def getByEmail(email: String): List[Task] = tasks
    }
  }

  class TasksController extends Tasks with FakeTaskRepo

  "TasksController" should "require logged on user for index action" in {
    val result = new TasksController().index().apply(FakeRequest())
    status(result) should be(303)
  }

  it should "require logged on user for tasks action" in {
    val result = new TasksController().tasks().apply(FakeRequest())
    status(result) should be(303)
  }

  it should "require logged on user for new task action" in {
    val result = new TasksController().newTask().apply(FakeRequest())
    status(result) should be(303)
  }

  it should "require logged on user for delete task action" in {
    val result = new TasksController().deleteTask(1).apply(FakeRequest())
    status(result) should be(303)
  }

  it should "require logged on user for update task action" in {
    val result = new TasksController().updateTask(1).apply(FakeRequest())
    status(result) should be(303)
  }

  it should "serve the tasks angularjs app" in new WithApplication {
    val result = new TasksController()
      .index()
      .apply(FakeRequest().withSession("user" -> "test@test.test"))

    status(result) should be(200)
    contentType(result) should be(Some("text/html"))
    contentAsString(result) should include("<div ng-app=\"todos\">")
    contentAsString(result) should include("My tasks")
    contentAsString(result) should include("test@test.test")
  }

  it should "return user's tasks" in new WithApplication {
    val ctrl = new TasksController()
    (ctrl.fake.getByEmail _)
      .expects("test@test.test")
      .returning(tasks)

    val result = ctrl
      .tasks()
      .apply(FakeRequest().withSession("user" -> "test@test.test"))

    status(result) should be(200)
    contentType(result) should be(Some("application/json"))
    contentAsString(result) should be(
      "[{\"id\":1,\"label\":\"blabla\",\"completed\":false,\"ownerId\":1},{\"id\":2,\"label\":\"completed\",\"completed\":true,\"ownerId\":1}]"
    )
  }

  it should "create task" in new WithApplication {
    val ctrl = new TasksController()
    (ctrl.fake.create _)
      .expects("test task", "test@test.test")
      .returning(Some(Task(Some(1), "test task", completed = false, 1)))

    val result = ctrl
      .newTask()
      .apply(FakeRequest()
      .withSession("user" -> "test@test.test")
      .withJsonBody(Json.parse("{\"label\":\"test task\"}")))

    status(result) should be(200)
    contentType(result) should be(Some("application/json"))
    contentAsString(result) should be(
      "{\"id\":1,\"label\":\"test task\",\"completed\":false,\"ownerId\":1}"
    )
  }

  it should "not create task if empty request" in new WithApplication {
    val result = new TasksController()
      .newTask()
      .apply(FakeRequest().withSession("user" -> "test@test.test"))

    status(result) should be(400)
    contentAsString(result) should be("Empty or wrong request")
  }

  it should "not create task if invalid json request" in new WithApplication {
    val result = new TasksController()
      .newTask()
      .apply(FakeRequest().withSession("user" -> "test@test.test").withJsonBody(Json.parse("{\"wrong\":\"json\"}")))

    status(result) should be(400)
    contentAsString(result) should be("No label found")
  }

  it should "not create task if request of invalid type" in new WithApplication {
    val result = new TasksController()
      .newTask()
      .apply(FakeRequest().withSession("user" -> "test@test.test").withFormUrlEncodedBody("nonsense" -> "nonsense"))

    status(result) should be(400)
    contentAsString(result) should be("Empty or wrong request")
  }

  it should "delete task of given id" in new WithApplication() {
    val ctrl = new TasksController()
    (ctrl.fake.delete _).expects(1: Long)

    val result = ctrl
      .deleteTask(1)
      .apply(FakeRequest()
      .withSession("user" -> "test@test.test"))

    status(result) should be(200)
  }

  it should "update task of given id with given content" in new WithApplication() {
    val updatedTask = new Task(Some(1), "UPDATED", true, 1)
    val ctrl = new TasksController()
    (ctrl.fake.update _).expects(1: Long, new Task(Some(1), "UPDATED", true, 1))
    (ctrl.fake.getById _).expects(1: Long).returning(Some(updatedTask))

    val result = ctrl
      .updateTask(1)
      .apply(FakeRequest()
      .withSession("user" -> "test@test.test")
      .withJsonBody(Json.parse("{\"id\":1,\"label\":\"UPDATED\",\"completed\":true,\"ownerId\":1}")))

    status(result) should be(200)
    contentType(result) should be(Some("application/json"))
    contentAsString(result) should be(
      "{\"id\":1,\"label\":\"UPDATED\",\"completed\":true,\"ownerId\":1}"
    )
  }

  it should "not update if invalid json" in new WithApplication() {
    val updatedTask = new Task(Some(1), "UPDATED", true, 1)
    val ctrl = new TasksController()

    val result = ctrl
      .updateTask(1)
      .apply(FakeRequest()
      .withSession("user" -> "test@test.test")
      .withJsonBody(Json.parse("{\"wrong\":\"json\"}")))

    status(result) should be(400)
    contentAsString(result) should be("Empty or bad request")
  }

  it should "not update if empty request" in new WithApplication() {
    val updatedTask = new Task(Some(1), "UPDATED", true, 1)
    val ctrl = new TasksController()

    val result = ctrl
      .updateTask(1)
      .apply(FakeRequest()
      .withSession("user" -> "test@test.test"))

    status(result) should be(400)
    contentAsString(result) should be("Empty or bad request")
  }
}


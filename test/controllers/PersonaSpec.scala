package controllers

import tests.UnitSpec
import play.api.mvc.SimpleResult
import scala.concurrent.{Future, future}
import play.api.test._
import play.api.test.Helpers._
import utils.components.PersonaServiceComponent
import utils.PersonaRequesterImpl
import models.components.UserRepositoryComponent
import models.database.User

class PersonaSpec extends UnitSpec {
  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  trait UserFakeRepository extends UserRepositoryComponent {
    def userRepository = new FakeRepository

    class FakeRepository extends UserRepository{

      def getById(id: Long): Option[User] = {
        Some(User(Some(1), "test@test.test"))
      }

      def getByEmail(email: String): Option[User] = {
        Some(User(Some(1), "test@test.test"))
      }

      def create(email: String): Unit = {}
    }
  }

  trait PersonaServiceMock extends PersonaServiceComponent with PersonaRequesterImpl {
    def result: (Boolean, String)
    def service = new MockServiceImpl(result)

    class MockServiceImpl(result:(Boolean,String)) extends PersonaService{
      override def authenticate(assertion: String): Future[(Boolean, String)] = {
        future {
          result
        }
      }
    }
  }

  trait SuccessVerificationService extends PersonaServiceMock {
    def result = (true, "test@test.test")
  }

  trait WrongAssertionService extends PersonaServiceMock {
    def result = (false, "Wrong assertion")
  }

  class PersonaSuccessController extends Persona with SuccessVerificationService with UserFakeRepository

  "Persona controller" should "create a session with user email" in new WithApplication {
    val result = runLogin(new PersonaSuccessController)
    status(result) should be(200)
    contentAsString(result) should be("test@test.test")
    session(result).get("user") should be(Some("test@test.test"))
  }

  class PersonaFailController extends Persona with WrongAssertionService with UserFakeRepository

  it should "return forbidden with reason if user not verified" in  {
    val result = runLogin(new PersonaFailController)
    status(result) should be(403)
    contentAsString(result) should be("Wrong assertion")
    session(result).get("user") should be(None)
  }

  it should "clear session on logout" in new WithApplication {
    val result = new PersonaSuccessController()
      .logout()
      .apply(FakeRequest().withSession("user" -> "test@test.test"))
    session(result).get("user") should be(None)
  }

  def runLogin(controller: Persona): Future[SimpleResult] = {
    controller.login().apply(FakeRequest().withFormUrlEncodedBody("assertion" -> "test"))
  }
}

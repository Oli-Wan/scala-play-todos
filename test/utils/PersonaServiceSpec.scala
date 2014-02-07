package utils

import tests.UnitSpec
import play.api.libs.json.{JsValue, Json}
import scala.concurrent.{Future, Await, future}
import scala.concurrent.duration.Duration
import utils.components.{PersonaServiceComponent, PersonaRequesterComponent}

class PersonaServiceSpec extends UnitSpec  {
  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  trait ServiceWrapper extends PersonaServiceComponent {
    def service = new PersonaServiceImpl

    class PersonaServiceImpl extends PersonaService
  }

  class MockRequestImpl(json: String) {
    def request(assertion: String):Future[JsValue] = {
      future {
        Json.parse(json)
      }
    }
  }

  trait SuccessRequest extends PersonaRequesterComponent{
    def requester =
      new MockRequestImpl("{\"status\":\"okay\",\"email\":\"test@test.test\"}")
        with PersonaRequester
  }

  class AuthSuccessfulService extends ServiceWrapper with SuccessRequest

  "PersonaService" should "get email if assertion is valid" in {
    authResultsOf(new AuthSuccessfulService) should be (true, "test@test.test")
  }

  trait FailRequest extends PersonaRequesterComponent {
    def requester =
      new MockRequestImpl("{\"status\":\"failure\",\"reason\":\"wrong assertion\"}")
        with PersonaRequester
  }

  class AuthDeniedService extends ServiceWrapper with FailRequest

  it should "return the failure type if assertion is invalid" in {
    authResultsOf(new AuthDeniedService) should be (false, "wrong assertion")
  }

  trait ErrorRequest extends PersonaRequesterComponent {
    override def requester = new ErrorRequestImpl

    class ErrorRequestImpl extends PersonaRequester {
      def request(assertion: String):Future[JsValue] = {
        future {
          throw new Exception("Server unreachable")
        }
      }
    }
  }

  class ErrorService extends ServiceWrapper with ErrorRequest

  it should "not crash if the verifier doesn't respond" in {
    authResultsOf(new ErrorService) should be (false, "Server unreachable")
  }

  def authResultsOf(wrapper: PersonaServiceComponent): (Boolean,String) = {
    Await.result(wrapper.service.authenticate("test"), Duration(10000, "millis"))
  }
}
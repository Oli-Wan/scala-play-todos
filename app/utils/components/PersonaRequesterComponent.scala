package utils.components

import scala.concurrent.Future
import play.api.libs.json.{Json, JsValue}
import play.api.libs.ws.WS

trait PersonaRequesterComponent {
  def requester: PersonaRequester

  trait PersonaRequester {
    def request(assertion: String):Future[JsValue]
  }
}
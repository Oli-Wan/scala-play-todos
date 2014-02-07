package utils

import utils.components.PersonaRequesterComponent
import scala.concurrent.Future
import play.api.libs.json.{Json, JsValue}
import play.api.libs.ws.WS
import play.api.Play
import play.api.Play.current

trait PersonaRequesterImpl extends PersonaRequesterComponent {
  def requester = new DefaultPersonaRequester

  class DefaultPersonaRequester extends PersonaRequester {
    val verifier = "https://verifier.login.persona.org/verify"
    implicit val context = scala.concurrent.ExecutionContext.Implicits.global

    def request(assertion: String):Future[JsValue] = {
      WS.url(verifier)
        .withHeaders("Content-Type" -> "application/x-www-form-urlencoded")
        .post(
          Map(
            "assertion" -> Seq(assertion),
            "audience" -> Seq(Play.application.configuration.getString("persona.audience").getOrElse("http://localhost:9000"))
          )
        ).map( response => Json.parse(response.body))
    }
  }
}

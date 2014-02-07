package utils.components

import scala.concurrent.Future

trait PersonaServiceComponent extends PersonaRequesterComponent  {
  def service: PersonaService

  trait PersonaService  {
    implicit val context = scala.concurrent.ExecutionContext.Implicits.global

    def authenticate(assertion: String): Future[(Boolean, String)] = {
      val result = requester.request(assertion)
      result.map(json => {
        (json \ "status").asOpt[String].map(_ == "okay") match {
          case Some(true) =>
            (json \ "email").asOpt[String] match {
              case Some(email) => (true, email)
              case None => (false, "Persona verifier not working correctly")
            }
          case Some(false) =>
            (false, (json \ "reason").asOpt[String].getOrElse("Unknown reason"))
        }
      }).recover {
        case e: Exception =>
          println("Could not parse the response from persona verifier")
          e.printStackTrace()
          (false, e.getMessage)
      }
    }
  }
}

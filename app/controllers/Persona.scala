package controllers

import play.api.mvc.{Controller, Action}
import utils.PersonaServiceImpl
import utils.components.PersonaServiceComponent
import models.components.UserRepositoryComponent
import models.slick.UserSlickRepository

trait Persona extends Controller with PersonaServiceComponent with UserRepositoryComponent {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  def login() = Action.async {
    implicit request =>
      service.authenticate(request.body.asFormUrlEncoded.get("assertion")(0))
        .map({
        case (true, email: String) =>
          userRepository.getByEmail(email).getOrElse(userRepository.create(email))
          Ok(email).withSession("user" -> email)
        case (false, reason: String) => Forbidden(reason)
      })
  }

  def logout() = Action {
    implicit request =>
      Ok("User logged out").withNewSession
  }
}

object Persona extends Persona with PersonaServiceImpl with UserSlickRepository
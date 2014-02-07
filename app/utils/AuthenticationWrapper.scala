package utils

import play.api.mvc.{SimpleResult, Request, ActionBuilder, Controller}
import scala.concurrent.Future

class AuthenticationWrapper extends Controller {

  object SecuredAction extends ActionBuilder[Request] {
    protected def invokeBlock[A](req: Request[A], action: (Request[A]) => Future[SimpleResult]): Future[SimpleResult] = {
      req.session.get("user") match {
        case Some(value) => action(req)
        case None => Future.successful(Redirect("/"))
      }
    }
  }

  object PublicAction extends ActionBuilder[Request] {
    protected def invokeBlock[A](req: Request[A], action: (Request[A]) => Future[SimpleResult]): Future[SimpleResult] = {
      req.session.get("user") match {
        case Some(value) => Future.successful(Redirect("/tasks/index"))
        case None => action(req)
      }
    }
  }
}

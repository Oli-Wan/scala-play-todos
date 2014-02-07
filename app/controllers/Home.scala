package controllers

import utils.AuthenticationWrapper

object Home extends AuthenticationWrapper {
  def index = PublicAction {
    implicit request =>
      Ok(views.html.home())
  }
}
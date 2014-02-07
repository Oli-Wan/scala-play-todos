package utils

import utils.components.PersonaServiceComponent

trait PersonaServiceImpl extends PersonaServiceComponent with PersonaRequesterImpl {
  def service = new DefaultPersonaService

  class DefaultPersonaService extends PersonaService
}

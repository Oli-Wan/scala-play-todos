
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
/**/
object home extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[play.api.mvc.Session,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/()(implicit session: play.api.mvc.Session):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.44*/("""

"""),_display_(Seq[Any](/*3.2*/main("Welcome")/*3.17*/ {_display_(Seq[Any](format.raw/*3.19*/("""
    <div class="row">
        <div class="small-12 columns">
            <h1>Please log in</h1>
            <h3>Through mozilla's persona unique id. It's the button in the upper bar! Click it, it's safe. ; )</h3>
        </div>
    </div>
""")))})))}
    }
    
    def render(session:play.api.mvc.Session): play.api.templates.HtmlFormat.Appendable = apply()(session)
    
    def f:(() => (play.api.mvc.Session) => play.api.templates.HtmlFormat.Appendable) = () => (session) => apply()(session)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Jan 31 09:32:21 CET 2014
                    SOURCE: /home/oli/Development/Play/todolist/app/views/home.scala.html
                    HASH: 37f767a6d57da103660200eb1856060fcabdf48a
                    MATRIX: 569->1|705->43|742->46|765->61|804->63
                    LINES: 19->1|22->1|24->3|24->3|24->3
                    -- GENERATED --
                */
            
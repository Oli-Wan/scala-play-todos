
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
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[play.api.mvc.Session,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/()(implicit session: play.api.mvc.Session):play.api.templates.HtmlFormat.Appendable = {
        _display_ {import helper._

def /*5.6*/script/*5.12*/:play.api.templates.HtmlFormat.Appendable = {_display_(

Seq[Any](format.raw/*5.16*/("""
        <script src=""""),_display_(Seq[Any](/*6.23*/routes/*6.29*/.Assets.at("javascripts/foundation/foundation.abide.js"))),format.raw/*6.85*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*7.23*/routes/*7.29*/.Assets.at("javascripts/foundation/foundation.alert.js"))),format.raw/*7.85*/("""" type="text/javascript"></script>

        <script src=""""),_display_(Seq[Any](/*9.23*/routes/*9.29*/.Assets.at("javascripts/app.js"))),format.raw/*9.61*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*10.23*/routes/*10.29*/.Assets.at("javascripts/controller/TodoCtrl.js"))),format.raw/*10.77*/("""" type="text/javascript"></script>
    """)))};
Seq[Any](format.raw/*1.44*/("""

"""),format.raw/*4.1*/("""
    """),format.raw/*11.6*/("""

"""),_display_(Seq[Any](/*13.2*/main("My tasks", script)/*13.26*/ {_display_(Seq[Any](format.raw/*13.28*/("""
    <div ng-app="todos">
        <div ng-controller="TodoCtrl">
            <div class="row">
                <div class="small-12 columns">
                    <h1>My tasks</h1>
                </div>
            </div>
            <div class="row" ng-repeat="message in messages">
                <div class="small-12 columns">
                    <div data-alert class="alert-box radius" ng-class="message.type">
                        """),format.raw/*24.25*/("""{"""),format.raw/*24.26*/("""{"""),format.raw/*24.27*/("""message.content"""),format.raw/*24.42*/("""}"""),format.raw/*24.43*/("""}"""),format.raw/*24.44*/("""
                        <a href="#" class="close" ng-click="close($index)">&times;</a>
                    </div>
                </div>
            </div>

            <form name="createTodoForm" ng-submit="createTodo(createTodoForm.$valid);" data-abide>
                <div class="row">
                    <fieldset>
                        <legend>Add a new task</legend>
                        <label for="label">Label <small>required</small></label>
                        <input type="text" id="label" ng-model="label" required />
                        <small class="error">Label is required and must be a string.</small>
                        <button type="submit">Create</button>
                    </fieldset>
                </div>
            </form>

            <div class="row">
                <div class="small-12 columns">
                    <h3>"""),format.raw/*44.25*/("""{"""),format.raw/*44.26*/("""{"""),format.raw/*44.27*/("""todos.length"""),format.raw/*44.39*/("""}"""),format.raw/*44.40*/("""}"""),format.raw/*44.41*/(""" task(s)</h3>
                </div>
                <hr/>
            </div>

            <div class="row" ng-repeat="todo in todos">
                <div class="small-2 columns">
                    <input type="checkbox" ng-model="todo.completed" ng-change="completeTodo(todo)"/>
                </div>
                <div class="small-5 medium-6 large-8 columns" ng-class=""""),format.raw/*53.73*/("""{"""),format.raw/*53.74*/("""strike: todo.completed"""),format.raw/*53.96*/("""}"""),format.raw/*53.97*/("""">
                    """),format.raw/*54.21*/("""{"""),format.raw/*54.22*/("""{"""),format.raw/*54.23*/(""" todo.label """),format.raw/*54.35*/("""}"""),format.raw/*54.36*/("""}"""),format.raw/*54.37*/("""
                </div>
                <div class="small-5 medium-4 large-2 columns" >
                    <button ng-click="deleteTodo($index)" class="small alert expand">Delete</button>
                </div>
                <hr/>
            </div>
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
                    SOURCE: /home/oli/Development/Play/todolist/app/views/index.scala.html
                    HASH: 09cb97c9efef779918474e94b8b769c1714a4301
                    MATRIX: 570->1|705->68|719->74|803->78|861->101|875->107|952->163|1044->220|1058->226|1135->282|1228->340|1242->346|1295->378|1388->435|1403->441|1473->489|1552->43|1580->62|1612->529|1650->532|1683->556|1723->558|2192->999|2221->1000|2250->1001|2293->1016|2322->1017|2351->1018|3253->1892|3282->1893|3311->1894|3351->1906|3380->1907|3409->1908|3815->2286|3844->2287|3894->2309|3923->2310|3974->2333|4003->2334|4032->2335|4072->2347|4101->2348|4130->2349
                    LINES: 19->1|22->5|22->5|24->5|25->6|25->6|25->6|26->7|26->7|26->7|28->9|28->9|28->9|29->10|29->10|29->10|31->1|33->4|34->11|36->13|36->13|36->13|47->24|47->24|47->24|47->24|47->24|47->24|67->44|67->44|67->44|67->44|67->44|67->44|76->53|76->53|76->53|76->53|77->54|77->54|77->54|77->54|77->54|77->54
                    -- GENERATED --
                */
            
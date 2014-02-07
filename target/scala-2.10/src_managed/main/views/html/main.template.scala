
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
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template4[String,Html,Html,play.api.mvc.Session,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(title: String, scripts: Html = Html(""))(content: Html)(implicit session: play.api.mvc.Session):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.98*/("""

<!DOCTYPE html>

<html>
    <head>
        <title>"""),_display_(Seq[Any](/*7.17*/title)),format.raw/*7.22*/("""</title>

        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">

        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*12.54*/routes/*12.60*/.Assets.at("stylesheets/main.css"))),format.raw/*12.94*/("""">
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*13.59*/routes/*13.65*/.Assets.at("images/favicon.png"))),format.raw/*13.97*/("""">

            <!-- foundation -->
        <link rel="stylesheet" href=""""),_display_(Seq[Any](/*16.39*/routes/*16.45*/.Assets.at("styles/normalize.css"))),format.raw/*16.79*/("""">
        <link rel="stylesheet" href=""""),_display_(Seq[Any](/*17.39*/routes/*17.45*/.Assets.at("styles/foundation.css"))),format.raw/*17.80*/("""">

        <link rel="stylesheet" href=""""),_display_(Seq[Any](/*19.39*/routes/*19.45*/.Assets.at("styles/main.css"))),format.raw/*19.74*/("""">
    </head>
    <body>
        <nav class="top-bar" data-topbar>
            <ul class="title-area">
                <li class="name">
                    <h1>
                        <a href="/">Scala todo app</a>
                    </h1>
                </li>
            </ul>
            <section class="top-bar-section">
                <ul class="right">
                    """),_display_(Seq[Any](/*32.22*/if(session.get("user") == None)/*32.53*/ {_display_(Seq[Any](format.raw/*32.55*/("""
                        <li class="has-form">
                            <a  id="signin" class="button">Sign in with Mozilla Persona</a>
                        </li>
                    """)))})),format.raw/*36.22*/("""
                    """),_display_(Seq[Any](/*37.22*/if(session.get("user") != None)/*37.53*/ {_display_(Seq[Any](format.raw/*37.55*/("""
                        <li>
                            <a href="/tasks/index">
                                Logged in as """),_display_(Seq[Any](/*40.47*/session/*40.54*/.get("user"))),format.raw/*40.66*/("""
                            </a>
                        </li>
                        <li class="has-form">
                            <a class="button" id="signout">Sign out</a>
                        </li>
                    """)))})),format.raw/*46.22*/("""
                </ul>
            </section>
        </nav>

        """),_display_(Seq[Any](/*51.10*/content)),format.raw/*51.17*/("""

        <script src=""""),_display_(Seq[Any](/*53.23*/routes/*53.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*53.74*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*54.23*/routes/*54.29*/.Assets.at("javascripts/foundation/foundation.js"))),format.raw/*54.79*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*55.23*/routes/*55.29*/.Assets.at("javascripts/foundation/foundation.topbar.js"))),format.raw/*55.86*/("""" type="text/javascript"></script>


        <script src=""""),_display_(Seq[Any](/*58.23*/routes/*58.29*/.Assets.at("javascripts/angular.js"))),format.raw/*58.65*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*59.23*/routes/*59.29*/.Assets.at("javascripts/angular-resource.js"))),format.raw/*59.74*/("""" type="text/javascript"></script>

        """),_display_(Seq[Any](/*61.10*/scripts)),format.raw/*61.17*/("""

        <script>
        $(document).foundation();
        </script>

        <script src="https://login.persona.org/include.js"></script>
        <script>
            var userFromSession =  '"""),_display_(Seq[Any](/*69.38*/session/*69.45*/.get("user"))),format.raw/*69.57*/("""';
            var personaUser = userFromSession.length > 0 ? userFromSession:null;
        </script>
        <script src=""""),_display_(Seq[Any](/*72.23*/routes/*72.29*/.Assets.at("javascripts/persona.js"))),format.raw/*72.65*/("""" type="text/javascript"></script>

        <div class="waiter transition">
            <h1>Please wait while we are login you
                <span class="in">
                    in <br/> Just follow the instruction on the popup window.
                </span>
                <span class="out">out .</span></h1>
        </div>
    </body>
</html>
"""))}
    }
    
    def render(title:String,scripts:Html,content:Html,session:play.api.mvc.Session): play.api.templates.HtmlFormat.Appendable = apply(title,scripts)(content)(session)
    
    def f:((String,Html) => (Html) => (play.api.mvc.Session) => play.api.templates.HtmlFormat.Appendable) = (title,scripts) => (content) => (session) => apply(title,scripts)(content)(session)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Fri Jan 31 09:32:21 CET 2014
                    SOURCE: /home/oli/Development/Play/todolist/app/views/main.scala.html
                    HASH: 15fed425dc0aca966feaf919361d880c5593fd82
                    MATRIX: 586->1|776->97|864->150|890->155|1133->362|1148->368|1204->402|1301->463|1316->469|1370->501|1480->575|1495->581|1551->615|1628->656|1643->662|1700->697|1778->739|1793->745|1844->774|2266->1160|2306->1191|2346->1193|2568->1383|2626->1405|2666->1436|2706->1438|2870->1566|2886->1573|2920->1585|3185->1818|3292->1889|3321->1896|3381->1920|3396->1926|3463->1971|3556->2028|3571->2034|3643->2084|3736->2141|3751->2147|3830->2204|3925->2263|3940->2269|3998->2305|4091->2362|4106->2368|4173->2413|4254->2458|4283->2465|4514->2660|4530->2667|4564->2679|4724->2803|4739->2809|4797->2845
                    LINES: 19->1|22->1|28->7|28->7|33->12|33->12|33->12|34->13|34->13|34->13|37->16|37->16|37->16|38->17|38->17|38->17|40->19|40->19|40->19|53->32|53->32|53->32|57->36|58->37|58->37|58->37|61->40|61->40|61->40|67->46|72->51|72->51|74->53|74->53|74->53|75->54|75->54|75->54|76->55|76->55|76->55|79->58|79->58|79->58|80->59|80->59|80->59|82->61|82->61|90->69|90->69|90->69|93->72|93->72|93->72
                    -- GENERATED --
                */
            
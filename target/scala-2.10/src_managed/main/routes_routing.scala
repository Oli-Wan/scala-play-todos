// @SOURCE:/home/oli/Development/Play/todolist/conf/routes
// @HASH:f7573f15be50da97dbe9252ae2b5292c7204ca7d
// @DATE:Fri Jan 31 09:32:20 CET 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._


import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Home_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Persona_login1 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:10
private[this] lazy val controllers_Persona_logout2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
        

// @LINE:13
private[this] lazy val controllers_Tasks_index3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tasks/index"))))
        

// @LINE:14
private[this] lazy val controllers_Tasks_tasks4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tasks"))))
        

// @LINE:15
private[this] lazy val controllers_Tasks_newTask5 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tasks"))))
        

// @LINE:16
private[this] lazy val controllers_Tasks_deleteTask6 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tasks/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:17
private[this] lazy val controllers_Tasks_updateTask7 = Route("PUT", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tasks/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:20
private[this] lazy val controllers_Assets_at8 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Home.index"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Persona.login"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Persona.logout"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tasks/index""","""controllers.Tasks.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tasks""","""controllers.Tasks.tasks"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tasks""","""controllers.Tasks.newTask"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tasks/$id<[^/]+>""","""controllers.Tasks.deleteTask(id:Long)"""),("""PUT""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tasks/$id<[^/]+>""","""controllers.Tasks.updateTask(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Home_index0(params) => {
   call { 
        invokeHandler(controllers.Home.index, HandlerDef(this, "controllers.Home", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Persona_login1(params) => {
   call { 
        invokeHandler(controllers.Persona.login, HandlerDef(this, "controllers.Persona", "login", Nil,"POST", """ Login""", Routes.prefix + """login"""))
   }
}
        

// @LINE:10
case controllers_Persona_logout2(params) => {
   call { 
        invokeHandler(controllers.Persona.logout, HandlerDef(this, "controllers.Persona", "logout", Nil,"POST", """""", Routes.prefix + """logout"""))
   }
}
        

// @LINE:13
case controllers_Tasks_index3(params) => {
   call { 
        invokeHandler(controllers.Tasks.index, HandlerDef(this, "controllers.Tasks", "index", Nil,"GET", """ Tasks""", Routes.prefix + """tasks/index"""))
   }
}
        

// @LINE:14
case controllers_Tasks_tasks4(params) => {
   call { 
        invokeHandler(controllers.Tasks.tasks, HandlerDef(this, "controllers.Tasks", "tasks", Nil,"GET", """""", Routes.prefix + """tasks"""))
   }
}
        

// @LINE:15
case controllers_Tasks_newTask5(params) => {
   call { 
        invokeHandler(controllers.Tasks.newTask, HandlerDef(this, "controllers.Tasks", "newTask", Nil,"POST", """""", Routes.prefix + """tasks"""))
   }
}
        

// @LINE:16
case controllers_Tasks_deleteTask6(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Tasks.deleteTask(id), HandlerDef(this, "controllers.Tasks", "deleteTask", Seq(classOf[Long]),"DELETE", """""", Routes.prefix + """tasks/$id<[^/]+>"""))
   }
}
        

// @LINE:17
case controllers_Tasks_updateTask7(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Tasks.updateTask(id), HandlerDef(this, "controllers.Tasks", "updateTask", Seq(classOf[Long]),"PUT", """""", Routes.prefix + """tasks/$id<[^/]+>"""))
   }
}
        

// @LINE:20
case controllers_Assets_at8(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     
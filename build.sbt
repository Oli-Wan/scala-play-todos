import net.litola.SassPlugin

name := "todolist"

version := "1.0-SNAPSHOT"

scalaVersion := "2.10.2"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "com.typesafe.play" %% "play-slick" % "0.5.0.8",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"
)

play.Project.playScalaSettings ++ SassPlugin.sassSettings
name := "ScalaCreateNewsApp"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "org.scalikejdbc" %% "scalikejdbc" % "2.1.2",
  "mysql" % "mysql-connector-java" % "5.1.29",
  "ch.qos.logback"    %   "logback-classic" % "1.1.1"
)
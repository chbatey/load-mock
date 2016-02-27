scalaVersion := "2.11.7"

name := "load-test"

val serverDependencies = Seq(
  "com.typesafe.akka" %% "akka-http-core" % "2.4.2"
)

lazy val commonSettings = Seq(
  organization := "info.batey",
  scalaVersion := "2.11.7"
)

lazy val server = (project in file("server")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= serverDependencies
  )

lazy val core = (project in file("core")).
  settings(commonSettings: _*).
  settings(
    scalaVersion := "2.11.7"
  )


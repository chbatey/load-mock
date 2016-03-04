scalaVersion := "2.11.7"

name := "load-test"

val serverDependencies = Seq(
  "com.typesafe.akka"     %% "akka-http-core" % "2.4.2",
  "io.spray"              %% "spray-json"     % "1.3.2"
)

val serverTestDependencies = Seq(
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
)

val perfDependencies = Seq(
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.1.5" % "test",
  "io.gatling"            % "gatling-test-framework"    % "2.1.5" % "test"
)

lazy val commonSettings = Seq(
  organization := "info.batey",
  scalaVersion := "2.11.7"
)

lazy val server = (project in file("server")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= serverDependencies ++ serverTestDependencies
  )

lazy val core = (project in file("core")).
  settings(commonSettings: _*).
  settings(
    scalaVersion := "2.11.7"
  )

lazy val perf = (project in file("perf")).
  enablePlugins(GatlingPlugin).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= perfDependencies
  )



name := """phone-book"""
organization := "com.github.wibl"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
    guice,
    "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test,
    "com.typesafe.play" %% "play-slick" % "5.0.0-RC2",
    "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0-RC2",
    "com.h2database" % "h2" % "1.4.200"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.github.wibl.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.github.wibl.binders._"

import sbt._
import sbt.Keys._
import spray.revolver.RevolverPlugin._

object Build extends Build {

  val appName          = "BlackBoxSociety"
  val appVersion       = "1.0"
  val scalaLangVersion = "2.10.3"

  val root = Project("root", file(".")).settings(
    name                  := appName,
    version               := appVersion,
    scalaVersion          := scalaLangVersion,
    //scalacOptions       += "-feature",
    //scalacOptions       += "-deprecation",
    scalacOptions in Test ++= Seq("-Yrangepos"),
    resolvers             += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
    resolvers             ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo),
    libraryDependencies   += "org.scalaz"        %% "scalaz-core" % "7.0.6",
    libraryDependencies   += "org.scalaz"        %% "scalaz-effect" % "7.0.6",
    libraryDependencies   += "org.scalaz"        %% "scalaz-concurrent" % "7.0.6",
    libraryDependencies   += "org.scalaz"        %% "scalaz-iteratee" % "7.0.6",
    libraryDependencies   += "com.typesafe.play" % "play-json_2.10" % "2.2.0-RC1",
    libraryDependencies   += "org.specs2"        %% "specs2" % "2.3.11" % "test"
  )

}

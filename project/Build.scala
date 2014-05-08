import sbt._
import sbt.Keys._

import bintray.Plugin._
import bintray.Keys._
import spray.revolver.RevolverPlugin._
import less.Plugin._
import sbtclosure.SbtClosurePlugin._
import sbtclosure.SbtClosurePlugin.ClosureKeys._
import less.Plugin.LessKeys._

object Build extends Build {

  val customBintraySettings = bintrayPublishSettings ++ Seq(
    packageLabels in bintray       := Seq("web", "mvc", "framework"),
    bintrayOrganization in bintray := Some("blackboxsociety"),
    repository in bintray          := "releases"
  )

  val exportableSettings = Revolver.settings ++ lessSettings ++ closureSettings ++ Seq(
    //crossPaths := false,
    (resourceManaged in (Compile, less)) <<= (crossTarget in Compile)(_ / "resource_managed" / "main" / "public" / "css"),
    (resourceManaged in (Compile, closure)) <<= (crossTarget in Compile)(_ / "resource_managed" / "main" / "public" / "javascript"),
    (filter in (Compile, less)) := "main.less"
  )

  val root = Project("root", file("."))
    .settings(customBintraySettings: _*)
    .settings(exportableSettings: _*)
    .settings(
      name                  := "blackbox",
      organization          := "com.blackboxsociety",
      version               := "0.1.0",
      scalaVersion          := "2.11.0",
      licenses              += ("MIT", url("http://opensource.org/licenses/MIT")),
      //scalacOptions       += "-feature",
      //scalacOptions       += "-deprecation",
      scalacOptions in Test ++= Seq("-Yrangepos"),
      resolvers             += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
      resolvers             += "Black Box Society Repository" at "http://dl.bintray.com/blackboxsociety/releases",
      resolvers             ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo),
      libraryDependencies   += "org.scalaz"          %% "scalaz-core" % "7.0.6",
      libraryDependencies   += "org.scalaz"          %% "scalaz-effect" % "7.0.6",
      libraryDependencies   += "org.scalaz"          %% "scalaz-concurrent" % "7.0.6",
      libraryDependencies   += "org.scalaz"          %% "scalaz-iteratee" % "7.0.6",
      libraryDependencies   += "com.typesafe.play"   % "play-json_2.10" % "2.2.0-RC1",
      libraryDependencies   += "org.specs2"          %% "specs2" % "2.3.11" % "test",
      libraryDependencies   += "com.blackboxsociety" %% "waterhouse" % "0.2.0"
    )

}

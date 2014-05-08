Black Box Framework
===================

[![Build Status](https://travis-ci.org/blackboxsociety/BlackBox.svg?branch=master)](https://travis-ci.org/blackboxsociety/bbs-web)

The Black Box Framework is a lightweight, fully asynchronous, single threaded
MVC web framework that leverages scalaz's purely functional asynchronous
constructs for concurrency.

Philosophy and Inspirations
---------------------------

This framework's architecture was inspired by the node.js architecture with
an abrupt departure from their concurrencly model. Which is to say that this
framework has a single threaded fully asynchronous core while using composable
and functionally pure concurrency abstractions via scalaz instead of using
callback based concurrency.

The middleware system this framework uses was driven by the middleware
implementation of clojure's ring spec. The ring spec allows for a standard,
simple and highly reusable method for creating and deploying middleware
on a per controller and global basis in a non intrusive manner.

Get Started
-----------

The following are temporary installation instructions. The installation
process will become simpler once we finish implementing the SBT plugin
for the framework.

Add the following to your `project/build.sbt`
```scala
addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.1")

addSbtPlugin("org.ensime" % "ensime-sbt-cmd" % "0.1.2")

resolvers += Resolver.url(
  "bintray-sbt-plugin-releases",
  url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(
    Resolver.ivyStylePatterns)

resolvers += "softprops-maven" at "http://dl.bintray.com/content/softprops/maven"

addSbtPlugin("me.lessis" % "less-sbt" % "0.2.2")

addSbtPlugin("org.scala-sbt" % "sbt-closure" % "0.1.4")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

```

Make your `project/Build.scala` look like the following:
```scala
import sbt._
import sbt.Keys._

import spray.revolver.RevolverPlugin._
import less.Plugin._
import sbtclosure.SbtClosurePlugin._
import sbtclosure.SbtClosurePlugin.ClosureKeys._
import less.Plugin.LessKeys._
import sbtassembly.Plugin._

object Build extends Build {

  val appSettings = Revolver.settings ++ lessSettings ++ closureSettings ++ Seq(
    crossPaths := false,
    (resourceManaged in (Compile, less)) <<= (crossTarget in Compile)(_ / "resource_managed" / "main" / "public" / "css"),
    (resourceManaged in (Compile, closure)) <<= (crossTarget in Compile)(_ / "resource_managed" / "main" / "public" / "javascript"),
    (filter in (Compile, less)) := "main.less"
  )

  val root = Project("root", file("."))
    .settings(appSettings: _*)
    .settings(assemblySettings: _*)
    .settings(
      name                  := "appName",
      organization          := "com.organizationName",
      version               := "1.0.0",
      scalaVersion          := "2.10.3",
      resolvers             += "Black Box Society Repository" at "http://dl.bintray.com/blackboxsociety/releases",
      libraryDependencies   += "com.blackboxsociety" %% "blackbox" % "0.1.0"
    )

}

```

Example
-------

An example application that demonstrates how to structure and configure
a typical Black Box application can be found
[here](https://github.com/blackboxsociety/blackbox-example).

Hack
----

PRs and issue submission/participation welcome.

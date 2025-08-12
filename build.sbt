name := "Scala Assessment"

organization := "assessment"

version := "1.0.0"

scalaVersion := "2.11.12" // upgraded from 2.11.7 iot. use ammonite

// Twitter Server
resolvers += "twttr" at "https://maven.twttr.com/"

libraryDependencies ++= {
  val akkaVersion      = "2.4.2"
  val scalaTestVersion = "2.2.6"
  val finatraVersion   = "2.1.6"

  Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,

    // Finatra Http
    "com.twitter.finatra" %% "finatra-http" % finatraVersion,
    "com.twitter.finatra" %% "finatra-httpclient" % finatraVersion,
    "com.twitter.finatra" %% "finatra-slf4j" % finatraVersion,
    "com.twitter" %% "finagle-stats" % "21.2.0" excludeAll(
      ExclusionRule("asm") // exclude because of conflict creating test report with PegDown
    ),

    // https://mvnrepository.com/artifact/com.twitter/bijection-core_2.10
    // seems to also be able to perform future conversion.
    //
    // "com.twitter" % "bijection-core_2.11" % "0.9.2",
    // "com.twitter" % "bijection-util_2.11" % "0.9.2",

    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-slf4j"  % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",

      // Test
    "org.scalatest" % "scalatest_2.11" % scalaTestVersion % "test",
    "org.pegdown" % "pegdown" % "1.6.0" % "test",         // needed by scalatest for html report
    "org.scalacheck" %% "scalacheck" % "1.12.6" % "test", // needed by scalatest for property based tests
    "org.testng" % "testng" % "6.8.21" % "test",
    "org.hamcrest" % "hamcrest-all" % "1.3" % "test",
    "org.mockito" % "mockito-core" % "2.0.111-beta" % "test",
    "com.github.sbt" % "junit-interface" % "0.13.3" % "test",

    // Finatra test utils -- The Full Monty. All is needed.
    "com.twitter.finatra" %% "finatra-http" % finatraVersion % "test",
    "com.twitter.finatra" %% "finatra-jackson" % finatraVersion % "test",
    "com.twitter.inject" %% "inject-server" % finatraVersion % "test",
    "com.twitter.inject" %% "inject-app" % finatraVersion % "test",
    "com.twitter.inject" %% "inject-core" % finatraVersion % "test",
    "com.twitter.inject" %% "inject-modules" % finatraVersion % "test",
    "com.google.inject.extensions" % "guice-testlib" % "4.2.3" % "test",

    "com.twitter.finatra" %% "finatra-http" % finatraVersion % "test" classifier "tests",
    "com.twitter.finatra" %% "finatra-jackson" % finatraVersion % "test" classifier "tests",
    "com.twitter.inject" %% "inject-app" % finatraVersion % "test" classifier "tests",
    "com.twitter.inject" %% "inject-core" % finatraVersion % "test" classifier "tests",
    "com.twitter.inject" %% "inject-modules" % finatraVersion % "test" classifier "tests",
    "com.twitter.inject" %% "inject-server" % finatraVersion % "test" classifier "tests",
    "org.specs2" %% "specs2-mock" % "3.7.3" % "test"
  )
}

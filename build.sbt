organization := "org.zardina"
name := "spotify_tools"
version := "0.0.1-SNAPSHOT"
scalaVersion := "2.12.2"


val http4sVersion = "0.17.4"

// Only necessary for SNAPSHOT releases
resolvers += Resolver.sonatypeRepo("snapshots")

libraryDependencies ++= Seq(
  "org.apache.commons" % "commons-lang3" % "3.6",
  "commons-codec" % "commons-codec" % "1.10",
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "io.circe" %% "circe-generic" % "0.8.0",
  "io.circe" %% "circe-literal" % "0.8.0"
)

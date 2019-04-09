
name := "SparkSQl"

version := "0.1"

scalaVersion := "2.11.8"
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.1" % "provided",
  "org.apache.spark" %% "spark-streaming" % "2.3.1",
  "org.apache.spark" %% "spark-mllib" % "2.3.1",
"org.apache.spark" %% "spark-sql" % "2.3.1",
"org.apache.spark" %% "spark-streaming" % "2.3.1",
    "org.apache.bahir" %% "spark-streaming-twitter" % "2.0.0","org.twitter4j" % "twitter4j-core" % "3.0.3",
  "org.twitter4j" % "twitter4j-stream" % "3.0.3", "org.http4s" %% "http4s-blazeserver" % "http4sVersion",
  "org.http4s" %% "http4s-core" % "http4sVersion",
  "org.http4s" %% "http4s-server" % "http4sVersion",
  "org.http4s" %% "http4s-dsl" % "http4sVersion",
  "org.http4s" %% "http4s-argonaut" % "http4sVersion",
  "io.argonaut" %% "argonaut" % "6.0.4",
  "org.scalaz.stream" %% "scalaz-stream" % "0.6a"
)
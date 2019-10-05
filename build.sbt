name := "ProducerCustomer"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.apache.kafka" % "kafka-clients" % "2.3.0",
  "org.apache.spark" % "spark-streaming_2.12" % "2.4.3",
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.3",
  "com.typesafe" % "config" % "1.3.4"
)
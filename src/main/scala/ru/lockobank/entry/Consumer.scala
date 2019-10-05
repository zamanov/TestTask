package ru.lockobank.entry

import org.apache.kafka.common.serialization.IntegerDeserializer
import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}
import ru.lockobank.entry.util.KafkaConfig

object Consumer {
  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)
    val conf = new SparkConf().setAppName("Streaming Application").setMaster("local")
    val ssc = new StreamingContext(conf, Seconds(1))
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> KafkaConfig.get("url"),
      "key.deserializer" -> classOf[CustomTimestampDeserializer],
      "value.deserializer" -> classOf[IntegerDeserializer],
      "group.id" -> KafkaConfig.get("group.id"),
      "auto.offset.reset" -> KafkaConfig.get("auto.offset.reset")
    )
    val topicsSet = KafkaConfig.get("topic").split(",").toSet
    val streamingService = new StreamingService()
    val streamingOperations = new StreamingOperations
    val messages = streamingService.createStream(ssc, kafkaParams, topicsSet)
    val reducedStream = streamingOperations.reduceByWindow(messages)
    reducedStream.print()

    ssc.start()
    ssc.awaitTermination()
  }
}

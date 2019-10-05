package ru.lockobank.entry

import java.sql.Timestamp
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}

class StreamingService() {

  def createStream(ssc: StreamingContext, kafkaParams: Map[String, Object], topicsSet: Set[String]): InputDStream[ConsumerRecord[Timestamp, Integer]] = {
    KafkaUtils.createDirectStream(ssc, LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[Timestamp, Integer](topicsSet, kafkaParams))
  }
}
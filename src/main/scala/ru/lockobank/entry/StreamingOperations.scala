package ru.lockobank.entry

import java.sql.Timestamp
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.streaming.Seconds
import org.apache.spark.streaming.dstream.{DStream, InputDStream}

class StreamingOperations {
  def reduceByWindow(dStream: InputDStream[ConsumerRecord[Timestamp, Integer]]): DStream[Integer] = {
    dStream.map(_.value()).reduceByWindow((a, b) => a + b,
      Seconds(1),
      Seconds(1)
    )
  }
}

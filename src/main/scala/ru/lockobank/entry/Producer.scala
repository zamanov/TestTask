package ru.lockobank.entry

import java.sql.Timestamp
import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import ru.lockobank.entry.util.KafkaConfig
import scala.util.Random

object Producer {
  def main(args: Array[String]): Unit = {
    val topic = KafkaConfig.get("topic")
    val url = KafkaConfig.get("url")
    val props = new Properties()
    props.put("bootstrap.servers", url)
    props.put("key.serializer", "ru.lockobank.entry.CustomTimestampSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer")
    props.put("linger.ms", 1)

    val producer = new KafkaProducer[Timestamp, Integer](props)
    val rand = Random
    val start = 1
    val end = 10
    while (true) {
      val record = {
        new ProducerRecord[Timestamp, Integer](
          topic,
          new Timestamp(System.currentTimeMillis()),
          start + rand.nextInt(end - start + 1)
        )
      }
      producer.send(record)
    }
    producer.close()
  }
}

package ru.lockobank.entry

import java.sql.Timestamp
import org.apache.kafka.common.serialization.Deserializer
import org.codehaus.jackson.map.ObjectMapper

class CustomTimestampDeserializer extends Deserializer[Timestamp] {

  override def close(): Unit = {}

  override def deserialize(arg0: String, arg1: Array[Byte]): Timestamp = {
    val mapper: ObjectMapper = new ObjectMapper()
    var timestamp: Timestamp = null
    try timestamp = mapper.readValue(arg1, classOf[Timestamp])
    catch {
      case e: Exception => e.printStackTrace()
    }
    timestamp
  }

}

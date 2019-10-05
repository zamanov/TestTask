package ru.lockobank.entry

import java.sql.Timestamp
import org.apache.kafka.common.serialization.Serializer
import org.codehaus.jackson.map.ObjectMapper

class CustomTimestampSerializer extends Serializer[Timestamp]{

    override def serialize(arg0: String, arg1: Timestamp): Array[Byte] = {
      var retVal: Array[Byte] = null
      val objectMapper: ObjectMapper = new ObjectMapper()
      try retVal = objectMapper.writeValueAsString(arg1).getBytes
      catch {
        case e: Exception => e.printStackTrace()
      }
      retVal
    }

    override def close(): Unit = {}

}

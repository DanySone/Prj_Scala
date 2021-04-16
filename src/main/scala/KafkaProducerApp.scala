import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.TimerTask //used in order to produce a report every minute
import java.util.Timer

object KafkaProducerApp extends App {

  // Properties for Kafka producer
  val props:Properties = new Properties()
  props.put("bootstrap.servers","localhost:9092")
  props.put("key.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer",
    "org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks","all")

  //Instantiate producer
  val producer = new KafkaProducer[String, String](props)

  // Name of the topic that will be used to put messages
  val topic = "text_topic"

  // Instantiate drone report object
  val d1 = DroneReportObj.randrep()

  //Send drone report every minute
  val executingTask = new ClassExecutingTask
  executingTask.start()


  try {
    val record = new ProducerRecord[String, String](topic,
      d1._id.toString + ";"
        + d1._latitude + ";"
        + d1._longitude + ";"
        + d1._surrounding.toString + ";"
        + d1._words.toString + ";"
        + d1._day)
    val metadata = producer.send(record)
    }
  catch {
    case e:Exception => e.printStackTrace()
  } finally {
    producer.close()

  }
}
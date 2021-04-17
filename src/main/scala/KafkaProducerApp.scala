import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.Properties
import scala.concurrent.duration.{FiniteDuration, SECONDS}
import List._

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

  try {
    fill(50) {
      val d1 = DroneReportObj.randrep()
      val alert = d1._surrounding.filter((t) => t._2 < 50)
      if (alert.size == 1)
        println("Alert ! the citizen " + alert + "is unhappy, he must go to the peaceland prison.")
      else (alert.size > 1)
        println("Alert ! the citizens " + alert + "are unhappy, they must go to the peaceland prison.")
      val report = new ProducerRecord[String, String](topic,
        d1._id.toString + ";"
          + d1._latitude + ";"
          + d1._longitude + ";"
          + d1._surrounding.toString + ";"
          + d1._words.toString + ";"
          + d1._day)
      producer.send(report)
      Thread.sleep(60000L)
    }

  }
  catch {
    case e:Exception => e.printStackTrace()
  } finally {
    producer.close()

  }
}
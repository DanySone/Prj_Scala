import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.Properties


object KafkaProducerAlertRepApp extends App {

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
  val topic = "alert_report_topic"
  val partition_report = "0"
  val partition_alert = "1"
  def report_generator(p: KafkaProducer[String, String]) {
    val d1 = DroneReportObj.randrep()

    val alert = d1._surrounding.filter((t) => t._2 < 30)
    val alert_report = new ProducerRecord[String, String](topic ,
      partition_alert ,
      alert.keys + "/"
    + alert.values + ";")

    val report = new ProducerRecord[String, String](topic ,
      partition_report ,
      d1._id.toString + "/"
        + d1._latitude + "/"
        + d1._longitude + "/"
        + d1._surrounding.toString + "/"
        + d1._words.toString + "/"
        + d1._day + "/"
        + d1._surrounding.exists((t) => t._2 < 30) + ";")
    if (alert.keys != Set.empty) {
      val metadata_alert = p.send(alert_report)
    }
    val metadata_report = p.send(report)
    Thread.sleep(3000L)
    report_generator(p)
  }


  try {
    report_generator(producer)
  }
  catch {
    case e:Exception => e.printStackTrace()
  } finally {
    producer.close()

  }
}
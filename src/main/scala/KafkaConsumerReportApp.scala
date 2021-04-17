 import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

 import java.util
 import java.util.Properties
 import scala.collection.JavaConverters._

 object KafkaConsumerReportApp extends App {
   val props = new Properties()
   props.put("bootstrap.servers", "localhost:9092")
   props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
   props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
   props.put("group.id", "report")

   val kafkaConsumer = new KafkaConsumer[String, String](props)
   kafkaConsumer.subscribe(util.Collections.singletonList("alert_report_topic"))

   def records_print(records: ConsumerRecords[String, String]): Unit = {
     val rec = records.asScala.head.value()
     println(rec)
   }

   def records_val() {
     val records = kafkaConsumer.poll(5000)
     records_print(records)
     Thread.sleep(5000L)

     records_val()
   }

   records_val()
 }

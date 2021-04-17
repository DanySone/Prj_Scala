 import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
 import org.apache.kafka.common.TopicPartition

 import java.util
 import java.util.Properties
 import scala.collection.JavaConverters._

 object KafkaConsumerReportApp extends App {
   val props = new Properties()
   props.put("bootstrap.servers", "localhost:9092")
   props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
   props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
   props.put("group.id", "something")

   val kafkaConsumer = new KafkaConsumer[String, String](props)
   kafkaConsumer.assign(util.Collections.singleton(new TopicPartition("alert_report_topic", 0)))

   def records_print(): Unit = {
     try {
       val records = kafkaConsumer.poll(5000)
       val rec = records.asScala.head.value()
       println(rec)
     } catch {
       case e:Exception => Thread.sleep(5000)
     }
   }

   def records_val() {
     records_print()
     Thread.sleep(5000L)

     records_val()
   }

   records_val()
 }

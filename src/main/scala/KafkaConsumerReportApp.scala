 import KafkaConsumerApp.format
 import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
 import org.apache.kafka.common.TopicPartition

 import java.io.{File, FileOutputStream, PrintWriter}
 import java.text.SimpleDateFormat
 import java.util
 import java.util.{Calendar, Properties}
 import scala.collection.JavaConverters._

 object KafkaConsumerReportApp extends App {
   val props = new Properties()
   props.put("bootstrap.servers", "localhost:9092")
   props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
   props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
   props.put("group.id", "something")

   val kafkaConsumer = new KafkaConsumer[String, String](props)
   kafkaConsumer.assign(util.Collections.singleton(new TopicPartition("alert_report_topic", 0)))

   val format = new SimpleDateFormat("d_M_y")
   def records_print(): Unit = {
     try {
       val records = kafkaConsumer.poll(5000)
       val rec = records.asScala.head.value()
       println(rec)
       val pw = new PrintWriter(new FileOutputStream(new File("rapport_"+format.format(Calendar.getInstance().getTime()).toString+".csv"),true))
       pw.write(rec+"\n")
       pw.close
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

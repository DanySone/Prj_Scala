 import KafkaConsumerApp.format
 import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
 import org.apache.kafka.common.TopicPartition

 import java.io.{File, FileOutputStream, PrintWriter}
 import java.text.SimpleDateFormat
 import java.util
 import java.util.{Calendar, Properties}
 import scala.collection.JavaConverters._

 import org.apache.hadoop.conf.Configuration
 import org.apache.hadoop.fs.{FileSystem, Path}


 object KafkaConsumerReportApp extends App {
   val props = new Properties()
   props.put("bootstrap.servers", "localhost:9092")
   props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
   props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
   props.put("group.id", "something")

   val kafkaConsumer = new KafkaConsumer[String, String](props)
   kafkaConsumer.assign(util.Collections.singleton(new TopicPartition("alert_report_topic", 0)))

   val format = new SimpleDateFormat("d_M_y")

   def write(uri: String, filePath: String, data: Array[Byte]) = {
     // write file to hdfs
     // source : https://mariuszprzydatek.com/2015/05/10/writing-files-to-hadoop-hdfs-using-scala/
     System.setProperty("HADOOP_USER_NAME", "dany")
     val path = new Path(filePath)
     val conf = new Configuration()
     conf.set("fs.defaultFS", uri)
     val fs = FileSystem.get(conf)
     val os = fs.create(path)
     os.write(data)
     fs.close()
   }

   def records_print(): Unit = {
     try {
       val records = kafkaConsumer.poll(5000)
       val rec = records.asScala.head.value()
       println(rec)
       val pw = new PrintWriter(new FileOutputStream(new File("rapport_"+format.format(Calendar.getInstance().getTime()).toString+".csv"),true))
       pw.write(rec+"\n")
       pw.close
       write("hdfs://localhost:9000", "rapport_"+format.format(Calendar.getInstance().getTime()).toString+".csv", rec.getBytes)

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

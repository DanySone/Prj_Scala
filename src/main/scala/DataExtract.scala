 import org.apache.kafka.clients.consumer.KafkaConsumer
  import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
  import org.apache.kafka.common.serialization.StringDeserializer
  import java.util
  import scala.collection.JavaConverters._
  import java.util.Properties

  object DataExtract extends App {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "something")

    val kafkaConsumer = new KafkaConsumer[String, String](props)
    kafkaConsumer.subscribe(util.Collections.singletonList("text_topic"))

    while (true) {
      val records = kafkaConsumer.poll(1000)
      for (record <- records.asScala) {
        println(record.value())
      }
    }
}

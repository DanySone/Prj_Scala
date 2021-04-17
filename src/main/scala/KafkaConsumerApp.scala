 import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
 import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
 import org.apache.kafka.common.serialization.StringDeserializer

 import java.util
 import scala.collection.JavaConverters._
 import java.util.Properties

  object KafkaConsumerApp extends App {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "something")

    val kafkaConsumer = new KafkaConsumer[String, String](props)
    kafkaConsumer.subscribe(util.Collections.singletonList("text_topic"))

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

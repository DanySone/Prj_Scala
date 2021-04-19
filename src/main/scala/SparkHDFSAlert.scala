import SparkHDFS.{droneRepDF, spark}
import org.apache.spark.sql.functions._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}

case class AlertReport(latitude: String, longitude: String, name: String, peacescore: String)

object SparkHDFSAlert extends App {
  val conf = new SparkConf().setAppName("Read CSV File").setMaster("local[*]")
  val sc = new SparkContext(conf)
  val sqlContext = new SQLContext(sc)

  import sqlContext.implicits._
  val alertRDD = sc.textFile("hdfs://localhost:9000/c_alert_19_4_2021.csv")

  val alertRepRdd = alertRDD.map {
    line =>
      val line2 = line.substring(0, line.length()-1)
      val line3 = line2.replace("MapLike.DefaultValuesIterable", "")
      val line4 = line3.replace("Set", "")
      val line5 = line4.replace("(", "")
      val line6 = line5.replace(")", "")
      val col = line6.split("/")
      AlertReport(col(0), col(1), col(2), col(3))
  }

  val alertDF = alertRepRdd.toDF()
  alertDF.show()


  val spark2 = SparkSession
    .builder()
    .appName("Spark SQL basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()
  alertDF.createOrReplaceTempView("df")

  val sqlDF = spark2.sql("SELECT * from df ORDER BY 4")
  sqlDF.show()
}

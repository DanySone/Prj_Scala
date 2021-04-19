import org.apache.spark
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.sql.SparkSession

case class DroneReport(id: String, latitude: String, longitude: String, surrounding: String, words: String, day: String, alert: String)

object SparkHDFS extends App {

  val conf = new SparkConf().setAppName("Read CSV File").setMaster("local[*]")
  val sc = new SparkContext(conf)
  val sqlContext = new SQLContext(sc)

  import sqlContext.implicits._
  val reportRDD = sc.textFile("hdfs://localhost:9000/c_rapport_19_4_2021.csv")

  val droneRepRdd = reportRDD.map {
    line =>
      val line2 = line.substring(0, line.length()-1)
      val line3 = line2.replace("Map", "")
      val col = line3.split("/")
      DroneReport(col(0), col(1), col(2), col(3), col(4), col(5), col(6))
  }

  val droneRepDF = droneRepRdd.toDF()
  droneRepDF.show()

  val spark = SparkSession
    .builder()
    .appName("Spark SQL basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()
  droneRepDF.createOrReplaceTempView("df")

  val sqlDF = spark.sql("SELECT day, COUNT(*) as nbr_alert FROM df  WHERE alert = 'true' GROUP BY day ORDER BY 2 DESC;")
  sqlDF.show()
}

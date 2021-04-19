import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.{SQLContext, SparkSession}

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
      val col = line2.split("/")
      DroneReport(col(0), col(1), col(2), col(3), col(4), col(5), col(6))
  }

  val droneRepDF = droneRepRdd.toDF()
  droneRepDF.show()


}

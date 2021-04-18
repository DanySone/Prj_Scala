import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.collection.immutable.HashMap
import java.util.{Calendar, Date}
import scala.util.Random
import faker._

object DroneReportObj {

  case class DroneReport(_id : Int, _latitude : String, _longitude : String, _surrounding : Map[String, Int], _words : List[String], _day : String ) {
    val id = _id
    val latitude = _latitude
    val longitude = _longitude
    val surrounding = _surrounding
    val words = _words
    val day = _day
  }


  def randrep() = {
    val r = scala.util.Random
    val n = scala.util.Random.nextInt(20)
    val m = scala.util.Random.nextInt(10)
    val list_day = List("Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    val rday = r.nextInt(7)
    DroneReport(r.nextInt(100000),
      Address.latitude,
      Address.longitude,
      List.fill(m)((Name.name, r.nextInt(100))).toMap,
      Lorem.words(n),
      list_day(rday)
    )
  }

  def dronedisp(d: DroneReport) = {
    val rep = "id = " + d._id.toString + " latitude = " + d._latitude + " longitude = " + d._longitude + " surrounding = " + d._surrounding.toString + " words = " + d._words.toString+" day = "+d._day.toString
    println(rep)
  }
}
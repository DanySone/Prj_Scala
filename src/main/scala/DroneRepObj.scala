import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import scala.collection.immutable.HashMap
import java.util.{Calendar, Date}
import scala.util.Random

object DroneRepObj {

  case class DroneRep(_id : Int, _latitude : Float, _longitude : Float, _surrounding : HashMap[String, Int], _words : List[String]) {
    val id = _id
    val latitude = _latitude
    val longitude = _longitude
    val surrounding = _surrounding
    val words = _words
    val format = new SimpleDateFormat("H-d-M-y")
    val date = format.format(Calendar.getInstance().getTime())
  }


  def randrep() = {
    val r = scala.util.Random
    val n = scala.util.Random.nextInt(10)
    DroneRep(r.nextInt(10000), r.nextFloat(), r.nextFloat(), HashMap(r.alphanumeric.take(6).mkString -> r.nextInt(100)), List.fill(n)(r.alphanumeric.take(5).mkString))
  }

  def dronedisp(d: DroneRep) = {
    println("id = " + d._id.toString + " latitude = " + d._latitude + " longitude = " + d._longitude + " surrounding = " + d._surrounding.toString + " words = " + d._words.toString)

  }
}
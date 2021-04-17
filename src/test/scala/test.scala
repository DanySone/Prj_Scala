import java.text.SimpleDateFormat
import java.util.Calendar

object test extends App {
  val format = new SimpleDateFormat("d-M-y")
  println(format.format(Calendar.getInstance().getTime()))
}

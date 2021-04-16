import java.util.Date
import java.util.Timer
import java.util.TimerTask


object ClassExecutingTask extends App {
    val executingTask = new ClassExecutingTask
    executingTask.start()
}

class ClassExecutingTask {
  val delay: Long = 10 * 1000 // delay in milliseconds

  val task = new ClassExecutingTask
  var timer = new Timer("TaskName")

  def start(): Unit = {
    timer.cancel()
    timer = new Timer("TaskName")
    val executionDate = new Date // no params = now
    timer.scheduleAtFixedRate(task, executionDate, delay)
  }

  private class LoopTask extends TimerTask {
    override def run(): Unit = {
      System.out.println("This message will print every 10 seconds.")
    }
  }
}
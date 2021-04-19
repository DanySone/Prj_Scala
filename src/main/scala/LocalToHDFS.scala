import KafkaConsumerAlertApp.format

import java.text.SimpleDateFormat
import java.util.Calendar
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.hadoop.fs.FileUtil

import java.io.File
import java.nio.file.{Files, Paths, StandardCopyOption}

object LocalToHDFS extends App {

  def moveRenameFile(source: String, destination: String): Unit = {
    // source : https://alvinalexander.com/scala/how-to-move-rename-files-in-scala-directories/
    val path = Files.move(
      Paths.get(source),
      Paths.get(destination),
      StandardCopyOption.REPLACE_EXISTING
    )
    // could return `path`
  }

  val format = new SimpleDateFormat("d_M_y")
  val hadoopConf = new Configuration()
  hadoopConf.addResource(new Path("/home/dany/hadoop-3.2.2/etc/hadoop/core-site.xml"))
  val hdfs = FileSystem.get(hadoopConf)
  val filePath = "rapport_"+ format.format(Calendar.getInstance().getTime()).toString+".csv"
  val filePath2 = "c_rapport_" + format.format(Calendar.getInstance().getTime()).toString+".csv"

  val alertPath = "alert_" + format.format(Calendar.getInstance().getTime()).toString+".csv"
  val alertPath2 = "c_alert_" + format.format(Calendar.getInstance().getTime()).toString+".csv"
  moveRenameFile(filePath, filePath2)
  moveRenameFile(alertPath, alertPath2)
  val srcPath = new Path(filePath2)
  val destPath = new Path("/" + filePath2)

  val srcPath2 = new Path(alertPath2)
  val destPath2 = new Path("/" + alertPath2)
  hdfs.copyFromLocalFile(srcPath, destPath)
  hdfs.copyFromLocalFile(srcPath2, destPath2)

  hdfs.close()

}

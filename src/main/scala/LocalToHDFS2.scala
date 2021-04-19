import LocalToHDFS.{format, hadoopConf}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import org.apache.commons.io.IOUtils

import java.text.SimpleDateFormat
import java.util.Calendar;


object LocalToHDFS2 extends App {

    val format = new SimpleDateFormat("d_M_y")
    val hadoopconf = new Configuration();
    hadoopconf.addResource(new Path("/home/dany/hadoop-3.2.2/etc/hadoop/core-site.xml"))
    val fs = FileSystem.get(hadoopconf);
    val filePath = "rapport_"+ format.format(Calendar.getInstance().getTime()).toString+".csv"
    //Create output stream to HDFS file
    val outFileStream = fs.create(new Path("hdfs://localhost:9000/"+filePath))

    //Create input stream from local file
    val inStream = fs.open(new Path("file://" + filePath))

    IOUtils.copy(inStream, outFileStream)

    //Close both files
    inStream.close()
    outFileStream.close()
}

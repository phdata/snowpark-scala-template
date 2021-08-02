

import com.snowflake.snowpark.Session

import java.io.{File, InputStream}
import java.nio.file.Paths
import java.util.Properties
import scala.collection.JavaConverters._

object SnowparkApp {

    def main(args: Array[String]): Unit = {
        // FIXME - This could probably be more elegant
        val stream: InputStream = getClass.getResourceAsStream("snowflake.conf")
        val properties: Properties = new Properties()
        properties.load(stream)

        val session = Session.builder.configs(properties.asScala.asJava).create
        addDeps(session)

        // Uncomment below to test out UDFs
//         createPermanentUdf(session)
//         createInlinePermanentUdf(session)
    }

    private def addDeps(session: Session): Unit = {
        val PATH = Paths.get(".", "target", "dependency").toAbsolutePath.toString

        val lst = getListOfFiles(PATH)
        val filteredLst = lst.filterNot(_.matches("^.*snowpark(_original)?-[0-9.]+\\.jar$"))
        for(f <- filteredLst) {
            System.out.println("Adding dep:" + f)
            session.addDependency(f)
        }
    }

    def getListOfFiles(dir: String): List[String] = {
        val d = new File(dir)
        if (d.exists && d.isDirectory) {
             d.listFiles.filter(_.isFile).toList.map(_.getPath)
        } else {
             List[String]()
        }
    }

    def createPermanentUdf(session: Session): Unit = {
        session.sql("drop FUNCTION if EXISTS udfPerm(Int)").show()

        session.udf.registerPermanent("udfPerm", permanentUdfHandler _,  "STG")

        session.sql("select *, udfPerm(*) from values (10)").show()
    }

    def permanentUdfHandler(i: Int): Int = {
        i * i
    }

    def createInlinePermanentUdf(session: Session): Unit = {
        session.sql("drop FUNCTION if EXISTS udfInlinePerm(Int)").show()

        session.udf.registerPermanent("udfInlinePerm", (i: Int) => {
            i * 2
        },  "STG")

        session.sql("select *, udfInlinePerm(*) from values (10)").show()
    }

}
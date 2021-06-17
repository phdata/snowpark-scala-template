
import com.snowflake.snowpark._
import com.snowflake.snowpark.functions._
import com.snowflake.snowpark.types._

import scala.io.Source
import java.io.InputStream
import java.util.Properties
import java.util.Map


import scala.collection.JavaConverters._

object SnowparkApp {
    def main(args: Array[String]): Unit = {

        // FIXME - This could probably be more elegant
        val stream: InputStream = getClass.getResourceAsStream("/snowflake.conf")
        val properties: Properties = new Properties()
        properties.load(stream)

        val session = Session.builder.configs(properties.asScala.asJava).create
        
        // Uncomment below to test out UDFs
        // createPermanentUdf(session)
        // createInlinePermanentUdf(session)
    }


    def createPermanentUdf(session: Session) = {
        session.sql("drop FUNCTION if EXISTS udfPerm(Int)").show()

        session.udf.registerPermanent("udfPerm", permanentUdfHandler _,  "STG")

        session.sql("select *, udfPerm(*) from values (10)").show()
    }

    def permanentUdfHandler(i: Int) = {
        i * i
    }

    def createInlinePermanentUdf(session: Session) = {
        session.sql("drop FUNCTION if EXISTS udfInlinePerm(Int)").show()

        session.udf.registerPermanent("udfInlinePerm", (i: Int) => {
            i * 2
        },  "STG")

        session.sql("select *, udfInlinePerm(*) from values (10)").show()
    }

    def storedProcHandler(session: Session): Int  = {
        10
    }

    def dataframeSession(session: Session) = {
        // TODO
    }

}
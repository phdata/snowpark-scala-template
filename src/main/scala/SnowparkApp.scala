
import com.snowflake.snowpark._
import com.snowflake.snowpark.functions._
import com.snowflake.snowpark.types._

import scala.io.Source
import java.io.InputStream
import java.util.Properties
import java.util.Map


import scala.collection.JavaConverters._


class StoredProcClass {
    def handler(session: Session, str: String): String = {
        import session.implicits._

        return "success!"
    }
}

object SnowparkApp {
    def main(args: Array[String]): Unit = {

    val stream: InputStream = getClass.getResourceAsStream("/snowflake.conf")
    val properties: Properties = new Properties()
    properties.load(stream)

    val session = Session.builder.configs(properties.asScala.asJava).create
    createPermanentUdf(session)

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

    }

    
//   def execModel(session: Session) = {
//     "hello world"
//   }
    
//   def udfInit(session: Session) = {
//         import session.implicits._
//     session.range(1, 10, 2).show()

//     // Create a DataFrame with two columns ("c" and "d").
//     // val dummy = session.createDataFrame(Seq((1, 1), (2, 2), (3, 3))).toDF("c", "d")
//     // dummy.show()

//     // // Initialize the context once per invocation.
//     // val udfRepeatedInit = udf((i: Int) => (new Context).randomInt)
//     // dummy.select(udfRepeatedInit('c)).show()

//     // Initialize the serializable context only once,
//     // regardless of the number of times that the UDF is invoked.
//     // val sC = new SerContext
//     // val udfOnceInit = udf((i: Int) => sC.randomInt)

//     lazy val unserC = new Context
//     session.udf.registerPermanent("udfOnceInit_3", (i: Int) => {
//        unserC.randomInt = unserC.randomInt + 1
//        unserC.randomInt
//     },  "TEMP_SNOWPARK.RCOOP_SCHEMA.PMML")

//     // dummy.select(udfOnceInit('c)).show()

//     // Initialize the non-serializable context only once,
//     // regardless of the number of times that the UDF is invoked.
//     // lazy val unserC = new Context
//     // val udfOnceInitU = udf((i: Int) => unserC.randomInt)
//     // dummy.select(udfOnceInitU('c)).show()

//   }

  // def permUDF(session: Session) ={
    
  //   // schema
  //   val schema = StructType(
  //     StructField("PCLASS", LongType, nullable = true) ::
  //       StructField("AGE", LongType, nullable = true) ::
  //       StructField("SIBSP", LongType, nullable = true) ::
  //       StructField("PARCH", LongType, nullable = true) ::
  //       StructField("FARE", DoubleType, nullable = true) ::
  //       StructField("EMBARKED_C", LongType, nullable = true) ::
  //       StructField("EMBARKED_Q", LongType, nullable = true) ::
  //       StructField("EMBARKED_S", LongType, nullable = true) ::
  //       StructField("SEX_FEMALE", LongType, nullable = true) ::
  //       StructField("SEX_MALE", LongType, nullable = true) ::
  //       Nil
  //   )

  //   val model = new XGBPMML(getClass().getClassLoader().getResourceAsStream("XGB_titanic.pmml")).getModel()

  //   session.sql("create stage TEMP_SNOWPARK.RCOOP_SCHEMA.PMML3").collect()
  //   session.addDependency(s"C:/Users/coops/OneDrive/Documents/Snowflake/coop_snowflake/coop_demo/target/lib/pmml4s_2.12-0.9.11.jar")
  //   session.addDependency(s"C:/Users/coops/OneDrive/Documents/Snowflake/coop_snowflake/coop_demo/target/lib/spray-json_2.12-1.3.5.jar")

  //   session.udf.registerPermanent("predictTitanicSurv_newStage", (pclass: Long, age: Long, sibsp: Long,
  //                                parch: Long, fare: Double, embarked_c: Long,
  //                                embarked_q: Long, embarked_s: Long, sex_female:
  //                                Long, sex_male: Long) => {
  //     val v = Array[Any](pclass, age, sibsp, parch, fare, embarked_c, embarked_q, embarked_s, sex_female, sex_male)
  //     model.predict(v).last.asInstanceOf[Long]
  //   }, "TEMP_SNOWPARK.RCOOP_SCHEMA.PMML3")
  // }

}
// USE SCALA!  This doesn't work and was a pain to debug 










// package io.phdata;

// import com.snowflake.snowpark.Session;
// import scala.runtime.AbstractFunction1;
// import scala.reflect.api.TypeTags.TypeTag;
// import scala.reflect.macros.

// public class App 
// {
//     // Snowpark requires a zero-argument constructor or all public static methods
//     public App() {
//         super();
//     }
    
//     public static String udfEcho(String str) {
//         return str;
//     }

//     public static void main(String[] args) {
//         String snowflakeConfig = App.class.getClassLoader().getResource("snowflake.conf").getFile();
//         System.out.println(snowflakeConfig);
//         Session session = Session.builder()
//             .configFile(snowflakeConfig)
//             .create();
    
        
//         (new scala.reflect.api.JavaUniverse.JavaMirror()).
//         // Create a permanent UDF
//         session.udf().registerPermanent("udf_echo",
//             new AbstractFunction1<String,String>(){
//                 @Override
//                 public String apply(String str) {
//                     return App.udfEcho(str);
//                 }                
//             },
//             "@STG",
//             TypeTag<String>,
//             TypeTags.TypeTag[].class);
//     }
// }

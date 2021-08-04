# Snowpark project template

This project provides a quickstart template for a typical Snowpark project.  It is a Maven project that is also configured to properly package and upload dependencies to Snowflake in order to avoid missing class errors.  It demonstrates how to use an interactive session, create temporary UDFs, and create persistent UDFs.

## To use this template --

### Run as a Scala program
1. Copy `src/resources/snowflake.conf.template` to `src/resources/snowflake.conf` and fill in credentials
2. Run `mvn scala:run` to test things out
3. Modify this project to do what you need.


### Run as an executable JAR
_Note- the `./dependencies` directory must still be present for upload to Snowflake_
1. Copy `src/resources/snowflake.conf.template` to `src/resources/snowflake.conf` and fill in credentials
2. Run `mvn package` to generate the JAR file
3. Run `java -jar target\snowpark-template-1.0-SNAPSHOT.jar` to execute
4. Modify this project to do what you need.

## Files

Main files:
* `src/resources/snowflake.conf.template`: Credentials template
* `src/main/scala/SnowparkApp.scala`: Main file demonstrating interactive Snowpark sessions
* `src/main/sql/template.sql`: Demonstration of init code and creating an UDF directly from SQL using a JAR file handler

### Known issues
* Public-private key authentication not supported yet.
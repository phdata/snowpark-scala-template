# Snowpark project template

## To use this template --

1. Copy `src/resources/snowflake.conf.template` to `src/resources/snowflake.conf` and fill in credentials
2. Run `mvn scala:run` to test things out
3. Modify this project to do what you need.

Main files:
* `src/resources/snowflake.conf.template`: Credentials template
* `src/main/scala/SnowparkApp.scala`: Main file demonstrating interactive Snowpark sessions
* `src/main/sql/template.sql`: Demonstration of init code and creating an UDF directly from SQL using a JAR file handler

# Snowpark project template

## To use this template --

1. Clone this project and follow the directions in the README: https://bitbucket.org/phdata/snowflake_snowpark_lib
2. Copy `src/resources/snowflake.conf.template` to `src/resources/snowflake.conf` and fill in credentials
3. Run `mvn scala:run` to test things out
4. Modify this project to do what you need.

Main files:
- `src/resources/snowflake.conf.template`: Credentials template
- `src/main/scala/SnowparkApp.scala`: Main file demonstrating interactive Snowpark sessions
- `src/main/sql/template.sql`: Demonstration of init code and creating an UDF directly from SQL using a JAR file handler
- `src/main/java/`: *DO NOT USE* - An attempt to get Snowpark in Java and not Scala - this didn't work.
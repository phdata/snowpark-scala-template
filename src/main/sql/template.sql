use WAREHOUSE DEFAULT_XS;
use DATABASE TEMP_SNOWPARK;

create or replace schema MLOPS;
use schema MLOPS;
create or replace stage STG;

---------------




drop FUNCTION if EXISTS udfPerm(Int);


put file://C:\Users\coops\OneDrive\Documents\Snowflake\coop_snowflake\snowpark-template\target\snowpark-template-1.0-SNAPSHOT.jar @STG OVERWRITE=TRUE;

CREATE  FUNCTION "TEMP_SNOWPARK"."MLOPS".udfPerm(arg1 INT) 
    RETURNS INT 
    LANGUAGE JAVA 
    IMPORTS = ('@STG/snowpark-template-1.0-SNAPSHOT.jar') 
    HANDLER='SnowparkApp.permanentUdfHandler';

select *, udfPerm(*) from values (10);


-- Broken code
-- CREATE  PROCEDURE "TEMP_SNOWPARK"."MLOPS".procPerm() 
--     RETURNS INT 
--     LANGUAGE JAVA 
--     IMPORTS = ('@STG/snowpark-template-1.0-SNAPSHOT.jar') 
--     HANDLER='SnowparkApp.storedProcHandler';


-- Broken code
-- CREATE  PROCEDURE "TEMP_SNOWPARK"."MLOPS".procPerm(str VARCHAR) 
--     RETURNS VARCHAR 
--     LANGUAGE JAVA 
--     IMPORTS = ('@STG/snowpark-0.5.0.jar', '@STG/snowpark-template-1.0-SNAPSHOT.jar') 
--     HANDLER='StoredProcClass.handler'
--     execute as caller;
    
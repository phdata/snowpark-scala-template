use WAREHOUSE DEFAULT_XS;
use DATABASE TEMP_SNOWPARK;

use role role_rcoop;

create or replace schema MLOPS;
use schema MLOPS;
create or replace stage STG;

---------------
put file://C:\Users\coops\OneDrive\Documents\Snowflake\coop_snowflake\snowpark-template\target\snowpark-template-1.0-SNAPSHOT.jar @STG OVERWRITE=TRUE;

CREATE OR REPLACE FUNCTION "TEMP_SNOWPARK"."MLOPS".udfPerm(arg1 INT) 
    RETURNS INT 
    LANGUAGE JAVA 
    IMPORTS = ('@STG/snowpark-template-1.0-SNAPSHOT.jar') 
    HANDLER='SnowparkApp.permanentUdfHandler';

select *, udfPerm(*) from values (10);


-- Broken code
CREATE  PROCEDURE "TEMP_SNOWPARK"."MLOPS".procPerm() 
    RETURNS INT 
    LANGUAGE JAVA 
    IMPORTS = ('@STG/snowpark-template-1.0-SNAPSHOT.jar') 
    HANDLER='SnowparkApp.storedProcHandler';


-- Broken code
-- CREATE  PROCEDURE "TEMP_SNOWPARK"."MLOPS".procPerm(str VARCHAR) 
--     RETURNS VARCHAR 
--     LANGUAGE JAVA 
--     IMPORTS = ('@STG/snowpark-0.5.0.jar', '@STG/snowpark-template-1.0-SNAPSHOT.jar') 
--     HANDLER='StoredProcClass.handler'
--     execute as caller;
    
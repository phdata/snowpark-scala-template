FROM maven:3.8.3-jdk-11

LABEL maintainer="EAP Dev Team"

COPY pom.xml .
COPY src /src

RUN mvn package

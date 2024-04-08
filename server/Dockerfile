FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /
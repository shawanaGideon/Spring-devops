FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY server/src /app/src
COPY server/pom.xml /app
RUN mvn -f /app/pom.xml clean package

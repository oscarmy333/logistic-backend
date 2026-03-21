FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /logistic
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /logistic
COPY target/logistic.jar logistic.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "logistic.jar"]
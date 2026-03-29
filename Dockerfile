FROM maven:3.9.8-eclipse-temurin-17 AS builder
WORKDIR /logistic
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /logistic
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=builder /logistic/target/logistic-0.0.6-SNAPSHOT.jar logistic.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "logistic.jar"]
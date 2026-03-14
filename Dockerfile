FROM eclipse-temurin:17-jdk-alpine
WORKDIR /logistic
COPY target/logistic.jar logistic.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "logistic.jar"]
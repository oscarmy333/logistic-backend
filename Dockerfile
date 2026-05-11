FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN chmod +x mvnw
&& ./mvnw -q -DskipTests dependency:go-offline

COPY src ./src

RUN ./mvnw -q -DskipTests clean package

COPY --from=builder /logistic/target/logistic-0.0.8-SNAPSHOT.jar logistic.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "logistic.jar"]
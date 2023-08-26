FROM maven:3.8.6-eclipse-temurin-18 AS build
WORKDIR /app
COPY . .
RUN mvn -f pom.xml clean assembly:assembly

FROM gcr.io/distroless/java17
COPY --from=build /app/target/KilluaBot-1.0.0-jar-with-dependencies.jar /app/KilluaBot.jar
ENTRYPOINT ["java", "-jar", "/app/KilluaBot.jar"]

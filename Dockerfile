FROM maven AS build
WORKDIR /app
COPY . .
RUN maven -f pom.xml clean package

FROM openjdk:18-jre-slim
COPY --from=build /app/target/1.0.0
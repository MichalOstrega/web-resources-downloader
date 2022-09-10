FROM openjdk:11-slim-buster as build
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

RUN ./mvnw -B package

FROM eclipse-temurin:8-jdk-focal

COPY --from=build target/Downloader-0.0.1-SNAPSHOT.jar Downloader-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Downloader-0.0.1-SNAPSHOT.jar"]
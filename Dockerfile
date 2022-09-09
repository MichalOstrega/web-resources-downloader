FROM eclipse-temurin:8-jdk-focal
MAINTAINER most
COPY target/Downloader-0.0.1-SNAPSHOT.jar Downloader-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Downloader-0.0.1-SNAPSHOT.jar"]
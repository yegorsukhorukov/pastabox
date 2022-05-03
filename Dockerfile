FROM openjdk:14-jdk-alpine
MAINTAINER Sukhorukov Yegor
COPY target/pastabox-0.0.1-SNAPSHOT.jar pastabox.jar
ENTRYPOINT ["java", "-jar", "/pastabox.jar"]
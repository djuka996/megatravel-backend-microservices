FROM openjdk:8-jdk-alpine
MAINTAINER Stefan Bokic <stefanbokic731@gmail.com>
ADD target/Rating-0.0.1-SNAPSHOT.jar containerized-rating.jar
ENTRYPOINT ["java", "-jar", "/containerized-rating.jar"]
EXPOSE 8888
FROM openjdk:8-jdk-alpine
MAINTAINER Stefan Bokic <stefanbokic731@gmail.com>
ADD target/accommodationservice-0.0.1-SNAPSHOT.jar containerized-accommodation.jar
ENTRYPOINT ["java", "-jar", "/containerized-accommodation.jar"]
EXPOSE 6666
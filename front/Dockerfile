FROM openjdk:8-jdk-alpine
MAINTAINER Stefan Bokic <stefanbokic731@gmail.com>
ADD target/front-0.0.1-SNAPSHOT.jar containerized-front.jar
ENTRYPOINT ["java", "-jar", "/containerized-front.jar"]
EXPOSE 4567
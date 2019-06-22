FROM openjdk:8-jdk-alpine
MAINTAINER Stefan Bokic <stefanbokic731@gmail.com>
ADD target/MessagingService-0.0.1-SNAPSHOT.jar containerized-message.jar
ENTRYPOINT ["java", "-jar", "/containerized-message.jar"]
EXPOSE 7777
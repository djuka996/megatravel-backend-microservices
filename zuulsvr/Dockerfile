FROM openjdk:8-jdk-alpine
MAINTAINER Stefan Bokic <stefanbokic731@gmail.com>
ADD target/zuulsvr-0.0.1-SNAPSHOT.jar service-gateway.jar
ENTRYPOINT ["java", "-jar", "/service-gateway.jar"]
EXPOSE 8761


FROM openjdk:8-jdk-alpine
MAINTAINER Stefan Bokic <stefanbokic731@gmail.com>
ADD target/eurekasvr-0.0.1-SNAPSHOT.jar service-discovery.jar
ENTRYPOINT ["java", "-jar", "/service-discovery.jar"]
EXPOSE 8761
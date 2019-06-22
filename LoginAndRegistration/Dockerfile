FROM openjdk:8-jdk-alpine
MAINTAINER Stefan Bokic <stefanbokic731@gmail.com>
ADD target/LoginAndRegistration-0.0.1-SNAPSHOT.jar containerized-login.jar
ENTRYPOINT ["java", "-jar", "/containerized-login.jar"]
EXPOSE 8555
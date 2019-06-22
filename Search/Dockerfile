FROM openjdk:8-jdk-alpine
MAINTAINER Stefan Bokic <stefanbokic731@gmail.com>
ADD target/Search-0.0.1-SNAPSHOT.jar containerized-search.jar
ENTRYPOINT ["java", "-jar", "/containerized-search.jar"]
EXPOSE 4444
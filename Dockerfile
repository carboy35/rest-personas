FROM openjdk:latest
FROM maven:alpine
ADD target/onemarketer-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

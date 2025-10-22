FROM openjdk:17-jdk-alpine
WORKDIR /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]

FROM openjdk:17-jdk-alpine
COPY target/devops-0.1.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
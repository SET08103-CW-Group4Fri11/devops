FROM openjdk:17-jdk-alpine
COPY target/*.jar app.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "devops-0.1.0.1.jar"]

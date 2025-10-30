FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/devops-0.1.0.1.jar /app/
ENTRYPOINT ["java", "-jar", "/app/devops-0.1.0.1.jar"]
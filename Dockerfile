FROM openjdk:17-jdk-alpine
COPY ./target/devops-0.1.0.1.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "devops-0.1.0.1.jar"]
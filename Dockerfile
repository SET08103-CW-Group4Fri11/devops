FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/devops-0.1.0.1.jar /tmp/devops-0.1.0.1.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "devops-0.1.0.1.jar"]

FROM amazoncorretto:17
WORKDIR /tmp
COPY target/devops-0.1.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]

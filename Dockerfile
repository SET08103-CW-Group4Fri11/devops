FROM amazoncorretto:17-alpine
WORKDIR /tmp
COPY target/*-shaded.jar app.jar
ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]

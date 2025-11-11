FROM amazoncorretto:17
WORKDIR /tmp
COPY target/*-shaded.jar app.jar
ENTRYPOINT ["java", "-jar", "/tmp/app.jar"]

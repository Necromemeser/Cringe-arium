FROM eclipse-temurin:17-jre
COPY target/*.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:17-jre
COPY target/*.jar app.jar
EXPOSE 8080
EXPOSE 10000
ENTRYPOINT ["java", "-jar", "/app.jar"]

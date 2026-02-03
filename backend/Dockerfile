FROM eclipse-temurin:17-jdk

WORKDIR /app
COPY target/rubber-shop-backend.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

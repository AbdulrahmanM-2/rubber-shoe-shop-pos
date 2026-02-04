# Stage 1: Build the JAR
FROM maven:3.9.5-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the JAR
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
COPY --from=build /app/target/timeless-shoes-shop-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Environment variables
ENV SPRING_PROFILES_ACTIVE=prod
ENV DB_URL=
ENV DB_USERNAME=
ENV DB_PASSWORD=
ENV FLY_API_TOKEN=
ENV APP_ADMIN_FULLNAME=
ENV APP_ADMIN_USERNAME=
ENV APP_ADMIN_PASSWORD=
ENV APP_ADMIN_ROLE=

ENTRYPOINT ["java", "-jar", "app.jar"]

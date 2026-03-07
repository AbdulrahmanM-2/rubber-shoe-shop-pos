# ───────────────────────────────────────────
# Stage 1: Build the Spring Boot app
# ───────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy pom.xml first — better Docker layer caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Package — skip tests for faster builds
RUN mvn clean package -DskipTests

# ───────────────────────────────────────────
# Stage 2: Lightweight runtime image
# ───────────────────────────────────────────
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the built jar from stage 1
COPY --from=build /app/target/*.jar app.jar

# Railway injects $PORT dynamically — must honour it
ENV PORT=8080
EXPOSE ${PORT}

ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=${PORT}", "-jar", "app.jar"]

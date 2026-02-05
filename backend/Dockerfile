# -------------------------------
# Stage 1: Build the Spring Boot app
# -------------------------------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven config first to leverage cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy all source code (includes frontend in resources)
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# -------------------------------
# Stage 2: Run the application
# -------------------------------
FROM eclipse-temurin:17-jre-alpine

# Install dumb-init for proper signal handling
RUN apk add --no-cache dumb-init

WORKDIR /app

# Copy the JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Use dumb-init to handle signals
ENTRYPOINT ["dumb-init", "--", "java", "-Xmx512m", "-Dserver.port=8080", "-jar", "app.jar"]

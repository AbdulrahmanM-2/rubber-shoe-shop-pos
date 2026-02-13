# -------------------------------
# Stage 1: Build the Spring Boot app
# -------------------------------
FROM maven:3.9-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY backend/pom.xml ./pom.xml
RUN mvn dependency:go-offline -B

# Copy source code from backend
COPY backend/src ./src

# Package the application using production profile
RUN mvn clean package -Pproduction -DskipTests

# -------------------------------
# Stage 2: Run the application
# -------------------------------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Railway dynamic port
ENV PORT 8080
EXPOSE ${PORT}

# Run the app
ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=${PORT}", "-jar", "app.jar"]

# -------------------------------
# Stage 1: Build the Spring Boot app
# -------------------------------
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copy Maven config first to leverage cache
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Package the application with production profile
RUN mvn clean package -Pproduction -DskipTests

# -------------------------------
# Stage 2: Run the application
# -------------------------------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copy the JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Use Railway dynamic port
ENV PORT 8080
EXPOSE ${PORT}

ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=${PORT}", "-jar", "app.jar"]

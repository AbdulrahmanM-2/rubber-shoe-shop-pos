# ─────────────────────────────────────────────────────────────────
# Stage 1: Build the React frontend
# ─────────────────────────────────────────────────────────────────
FROM node:18-alpine AS frontend-build

WORKDIR /app

# Install dependencies first (better layer caching)
COPY frontend/package.json ./
RUN npm install

# Copy source and build
COPY frontend/ ./
RUN npm run build

# ─────────────────────────────────────────────────────────────────
# Stage 2: Build the Spring Boot backend
#          + embed the React build into static/
# ─────────────────────────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-17 AS backend-build

WORKDIR /app

# Dependency cache layer
COPY backend/pom.xml .
RUN mvn dependency:go-offline -B

# Copy backend source
COPY backend/src ./src

# Copy built React app into Spring Boot's static folder
# Spring Boot will serve these files automatically at /
COPY --from=frontend-build /app/build ./src/main/resources/static

# Package the jar (production profile, no tests)
RUN mvn clean package -DskipTests -Pproduction

# ─────────────────────────────────────────────────────────────────
# Stage 3: Lightweight runtime image
# ─────────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

COPY --from=backend-build /app/target/*.jar app.jar

# Railway injects $PORT — Spring Boot must listen on it
ENV PORT=8080
EXPOSE ${PORT}

ENTRYPOINT ["java", "-Xmx512m", "-Dserver.port=${PORT}", "-jar", "app.jar"]

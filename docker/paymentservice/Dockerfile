# Stage 1: Build the application
FROM gradle:8.12.0-jdk21 AS build

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

# Copy the source code
COPY src /app/src

# Build the application
RUN ./gradlew build --no-daemon

# Stage 2: Create the final image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/payment-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
# Stage 1: Build the application
FROM gradle:8.12.0-jdk21 AS build

# Set the working directory
WORKDIR /home/gradle/project

# Copy the Gradle wrapper and build files
COPY --chown=gradle:gradle ShopWarehouseService/gradle /home/gradle/project/gradle
COPY --chown=gradle:gradle ShopWarehouseService/gradlew /home/gradle/project/gradlew
COPY --chown=gradle:gradle ShopWarehouseService/build.gradle /home/gradle/project/build.gradle
COPY --chown=gradle:gradle ShopWarehouseService/settings.gradle /home/gradle/project/settings.gradle

# Copy the source code
COPY --chown=gradle:gradle ShopWarehouseService/src /home/gradle/project/src

# Build the application
RUN gradle clean build --no-daemon -x test

# Stage 2: Create the final image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /home/gradle/project/build/libs/*.jar app/WarehouseService.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app/WarehouseService.jar"]
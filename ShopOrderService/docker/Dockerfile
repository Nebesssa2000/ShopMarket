# Stage 1: Build the application
FROM gradle:8.12.0-jdk21 AS build

# Set the working directory
WORKDIR /home/gradle/project

# Copy the Gradle wrapper and build files
COPY --chown=gradle:gradle ShopOrderService/gradle /home/gradle/project/gradle
COPY --chown=gradle:gradle ShopOrderService/gradlew /home/gradle/project/gradlew
COPY --chown=gradle:gradle ShopOrderService/build.gradle /home/gradle/project/build.gradle
COPY --chown=gradle:gradle ShopOrderService/settings.gradle /home/gradle/project/settings.gradle

# Copy the source code
COPY --chown=gradle:gradle ShopOrderService/src /home/gradle/project/src

# Build the application
RUN gradle clean build --no-daemon -x test

# Stage 2: Create the final image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /home/gradle/project/build/libs/order-service-0.0.1-SNAPSHOT.jar app/OrderService.jar

# Expose the port the application runs on
EXPOSE 80 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app/OrderService.jar"]
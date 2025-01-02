# Stage 1: Build the application
FROM gradle:8.12.0-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/project
WORKDIR /home/gradle/project
RUN gradle clean build --no-daemon -x test

# Stage 2: Create the final image
FROM openjdk:21-jdk-slim

# Copy the JAR file from the build stage
COPY --from=build /home/gradle/project/build/libs/delivery-service-0.0.1-SNAPSHOT.jar app/DeliveryService.jar

# Expose the port the application runs on
EXPOSE 80 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app/DeliveryService.jar"]
# Use OpenJDK 17 base image
FROM openjdk:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy your Spring Boot jar to the container
COPY target/expenso-backend-0.0.1-SNAPSHOT.jar app.jar

# # Start the application
# ENTRYPOINT ["java", "-jar", "app.jar"]





# # Copy the built JAR file
# COPY target/*.jar app.jar

# Expose port 8080
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]

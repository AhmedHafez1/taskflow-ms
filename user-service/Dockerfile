# Use Java 21 base image
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Copy the jar file
COPY target/user-service-*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
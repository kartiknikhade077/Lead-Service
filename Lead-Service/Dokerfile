# Use OpenJDK 21 slim image
FROM openjdk:21-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the JAR built by Maven into the container
COPY target/Lead-Service-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8083
EXPOSE 8083

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]

# Use an official Java runtime as a parent image
FROM eclipse-temurin:17-jre-jammy

# Set working directory in the container
WORKDIR /app

# Download the .jar from Nexus
RUN apt-get update && apt-get install -y curl

# Replace <nexus-url>, <repository-path>, <artifact-id>, <version>, and <extension>
RUN curl -o app.jar "http://192.168.33.10:8081/repository/kaddem/tn/esprit/spring/kaddem/0.0.1/kaddem-0.0.1.jar"


# Expose the port that the application runs on
EXPOSE 9060

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

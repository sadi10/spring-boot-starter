# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the project's JAR file into the container
COPY target/spring-boot-project-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot app uses
EXPOSE 9191

# Set the command to run your app using the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]

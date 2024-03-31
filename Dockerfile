# base image containing Java 17
FROM openjdk:17-jdk-alpine

# working directory in container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/PatientUI-0.0.1-SNAPSHOT.jar /app/app.jar

# Port on which the application is running
EXPOSE 8083

# Command to execute when the container starts
CMD ["java", "-jar", "-Dserver.address=0.0.0.0", "-Dserver.port=8083", "app.jar"]

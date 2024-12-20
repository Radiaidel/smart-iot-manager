
# Base Image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy dependencies and the application jar
COPY target/*.jar app.jar

# Expose port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM maven:3.9.5-eclipse-temurin-17 AS build
#
#WORKDIR /app
#
#COPY pom.xml .
#RUN mvn dependency:go-offline -B
#
#COPY src ./src
#RUN mvn package -DskipTests
#
#FROM eclipse-temurin:17-jre-alpine
#
#WORKDIR /app
#
#COPY --from=build /app/target/smart-iot-manager-0.0.1-SNAPSHOT.jar ./app.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java", "-jar", "app.jar"]

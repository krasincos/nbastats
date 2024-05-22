FROM eclipse-temurin:17

WORKDIR /app

COPY . .

# Build the application
RUN ./gradlew bootJar

# Expose the port the application runs on
EXPOSE 8081

# Run the application
ENTRYPOINT ["java","-jar","build/libs/stats-logger-service-0.0.1-SNAPSHOT.jar"]
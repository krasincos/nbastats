FROM eclipse-temurin:17

WORKDIR /app

COPY . .

# Build the application
RUN ./gradlew bootJar

# Expose the port the application runs on
EXPOSE 8082

# Run the application
ENTRYPOINT ["java","-jar","build/libs/stats-processor-service-0.0.1-SNAPSHOT.jar"]
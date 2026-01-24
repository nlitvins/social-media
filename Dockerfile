# Build stage
FROM gradle:9.3-jdk25 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

# Run stage
FROM eclipse-temurin:25-jre-jammy
LABEL authors="Nikita"
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

# Create a non-root user
RUN useradd -m -u 1000 appuser
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]


# Stage 1: Build the Spring Boot application
FROM maven:3.9-eclipse-temurin-25 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/target/DemoProject-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 2001

ENTRYPOINT ["java", "-jar", "app.jar"]

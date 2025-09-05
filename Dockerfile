FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY . .
WORKDIR /app
RUN chmod +x ./mvnw
RUN ./mvnw package -DskipTests
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

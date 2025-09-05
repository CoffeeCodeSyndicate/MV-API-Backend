FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY api ./api
WORKDIR /app/api
RUN chmod +x ./mvnw
RUN ./mvnw package -DskipTests
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/api/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

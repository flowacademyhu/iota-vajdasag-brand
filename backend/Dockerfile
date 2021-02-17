FROM maven:3.6.3-openjdk-11-slim AS builder

WORKDIR /build
COPY . .
RUN mvn -B clean package

FROM openjdk:11-slim

WORKDIR /app
COPY --from=builder /build/target/*.jar /app/vajdasag-brand.jar

ENV server.port=8080

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "vajdasag-brand.jar"]


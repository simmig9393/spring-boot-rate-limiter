# Spring Boot Rate Limiter Service

A production-ready backend service implementing distributed rate limiting using:

- Spring Boot 3 + Java 17
- AOP
- Redis
- Bucket4j Token Bucket Algorithm
- Custom Annotation (@RateLimited)

## Run
mvn spring-boot:run

## Start Redis
docker compose up -d

## Example endpoint
GET http://localhost:8080/api/test/hello
Allowed 5 times per 30 seconds.

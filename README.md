# Spring Boot Rate Limiter Service

A production-ready rate-limiting implementation using:
- Java 17 + Spring Boot 3
- Bucket4j Token Bucket algorithm
- In-memory & Redis modes
- Custom annotation + AOP
- Distributed throttle support

## Example usage:
@RateLimited(capacity = 20, refillTokens = 20, refillSeconds = 30)

# spring-boot-rate-limiter
A reusable backend service that provides IP-based, user-based, and API-key-based rate limiting, using:  In-memory (local rate limiter)  Redis (distributed rate limiter)  Bucket4j token bucket algorithm  Spring Boot filter/interceptor  Custom annotations (@RateLimited)  Configurable policies (e.g., 100 req / 10 sec)

package com.example.ratelimiter.limiter;
@Component
@RequiredArgsConstructor
public class RedisRateLimiter {

    private final StringRedisTemplate redisTemplate;

    public boolean isEnabled() {
        return redisTemplate.getConnectionFactory() != null;
    }

    public boolean allowRequest(String key, int capacity, int refillTokens, int refillSeconds) {

        Long value = redisTemplate.opsForValue().increment(key);
        if (value == 1) {
            redisTemplate.expire(key, Duration.ofSeconds(refillSeconds));
        }

        return value <= capacity;
    }
}

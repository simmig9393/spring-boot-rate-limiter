package com.example.ratelimiter.limiter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisRateLimiter {

    private final StringRedisTemplate redis;

    public boolean enabled() {
        return redis.getConnectionFactory() != null;
    }

    public boolean allow(String key, int capacity, int refillTokens, int refillSeconds) {
        Long count = redis.opsForValue().increment(key);

        if (count == 1) {
            redis.expire(key, Duration.ofSeconds(refillSeconds));
        }

        return count <= capacity;
    }
}

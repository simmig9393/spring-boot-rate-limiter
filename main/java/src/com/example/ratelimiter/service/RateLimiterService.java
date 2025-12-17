package com.example.ratelimiter.service;

import com.example.ratelimiter.limiter.InMemoryRateLimiter;
import com.example.ratelimiter.limiter.RedisRateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final InMemoryRateLimiter memory;
    private final RedisRateLimiter redis;

    public boolean allowRequest(String key, int capacity, int refillTokens, int refillSeconds) {
        if (redis.enabled()) {
            return redis.allow(key, capacity, refillTokens, refillSeconds);
        }
        return memory.allow(key, capacity, refillTokens, refillSeconds);
    }
}

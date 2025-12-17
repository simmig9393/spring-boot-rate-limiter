package com.example.ratelimiter.limiter;
@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final InMemoryRateLimiter inMemory;
    private final RedisRateLimiter redis;

    public boolean allowRequest(
            String key,
            int capacity,
            int refillTokens,
            int refillSeconds
    ) {
        if (redis.isEnabled()) {
            return redis.allowRequest(key, capacity, refillTokens, refillSeconds);
        }
        return inMemory.allowRequest(key, capacity, refillTokens, refillSeconds);
    }
}

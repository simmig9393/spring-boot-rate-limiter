package com.example.ratelimiter.limiter;
@Component
public class InMemoryRateLimiter {

    private final ConcurrentHashMap<String, Bucket> cache = new ConcurrentHashMap<>();

    public boolean allowRequest(String key, int capacity, int refillTokens, int refillSeconds) {
        Bucket bucket = cache.computeIfAbsent(key, k -> createBucket(capacity, refillTokens, refillSeconds));
        return bucket.tryConsume(1);
    }

    private Bucket createBucket(int capacity, int refillTokens, int seconds) {
        Refill refill = Refill.intervally(refillTokens, Duration.ofSeconds(seconds));
        Bandwidth limit = Bandwidth.classic(capacity, refill);
        return Bucket.builder().addLimit(limit).build();
    }
}

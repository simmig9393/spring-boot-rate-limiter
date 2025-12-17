package com.example.ratelimiter.limiter;

import io.github.bucket4j.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryRateLimiter {

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    public boolean allow(String key, int capacity, int refillTokens, int refillSeconds) {
        Bucket bucket = buckets.computeIfAbsent(key,
                k -> Bucket.builder()
                        .addLimit(Bandwidth.classic(
                                capacity,
                                Refill.intervally(refillTokens, Duration.ofSeconds(refillSeconds))
                        ))
                        .build()
        );
        return bucket.tryConsume(1);
    }
}

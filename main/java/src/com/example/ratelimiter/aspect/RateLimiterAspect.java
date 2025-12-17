package com.example.ratelimiter.aspect;

import com.example.ratelimiter.annotation.RateLimited;
import com.example.ratelimiter.exception.RateLimitExceededException;
import com.example.ratelimiter.service.RateLimiterService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RateLimiterAspect {

    private final RateLimiterService service;

    @Around("@annotation(limit)")
    public Object rateLimit(ProceedingJoinPoint pjp, RateLimited limit) throws Throwable {
        String key = pjp.getSignature().toShortString();

        boolean allowed = service.allowRequest(
                key,
                limit.capacity(),
                limit.refillTokens(),
                limit.refillSeconds()
        );

        if (!allowed) {
            throw new RateLimitExceededException("Too Many Requests");
        }

        return pjp.proceed();
    }
}

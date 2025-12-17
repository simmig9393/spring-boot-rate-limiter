package com.example.ratelimiter.aspect;
@Aspect
@Component
@RequiredArgsConstructor
public class RateLimiterAspect {

    private final RateLimiterService rateLimiterService;

    @Around("@annotation(rateLimited)")
    public Object rateLimit(ProceedingJoinPoint pjp, RateLimited rateLimited) throws Throwable {
        String key = pjp.getSignature().toShortString();

        boolean allowed = rateLimiterService.allowRequest(
                key,
                rateLimited.capacity(),
                rateLimited.refillTokens(),
                rateLimited.refillSeconds()
        );

        if (!allowed) {
            throw new RateLimitExceededException("Too Many Requests");
        }

        return pjp.proceed();
    }
}

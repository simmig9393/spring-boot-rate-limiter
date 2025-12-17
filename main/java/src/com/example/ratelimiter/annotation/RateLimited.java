package com.example.ratelimiter.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimited {
    int capacity() default 5;
    int refillTokens() default 5;
    int refillSeconds() default 60;
}

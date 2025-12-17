package com.example.ratelimiter.controller;
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/hello")
    @RateLimited(capacity = 5, refillTokens = 5, refillSeconds = 60)
    public String hello() {
        return "Hello Rate Limier";
    }
}

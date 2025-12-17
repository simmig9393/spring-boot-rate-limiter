package com.example.ratelimiter.limiter;
import java.util.concurrent.*;
public class InMemoryRateLimiter { private ConcurrentHashMap<String,Integer> map = new ConcurrentHashMap<>(); }

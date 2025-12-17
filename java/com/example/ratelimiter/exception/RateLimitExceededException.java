package com.example.ratelimiter.exception;
public class RateLimitExceededException extends RuntimeException {
  public RateLimitExceededException(String m){super(m);}}

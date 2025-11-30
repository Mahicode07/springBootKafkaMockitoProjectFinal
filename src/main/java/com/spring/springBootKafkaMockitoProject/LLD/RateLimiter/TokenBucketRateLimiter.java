package com.spring.springBootKafkaMockitoProject.LLD.RateLimiter;

import java.time.Clock;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TokenBucketRateLimiter {

    private final int capacity;
    private final Duration period;
    private final int tokensPerPeriod;
    private final Clock clock;
    private final Map<String, TokenBucket> userTokenBucket = new HashMap<>();

    public TokenBucketRateLimiter(int capacity, Duration period, int tokensPerPeriod, Clock clock) {
        this.capacity = capacity;
        this.period = period;
        this.tokensPerPeriod = tokensPerPeriod;
        this.clock = clock;
    }

    public boolean allowed(String userId) {
        TokenBucket bucket = userTokenBucket.computeIfAbsent(userId, k -> new TokenBucket(clock.millis(), tokensPerPeriod, capacity, period, tokensPerPeriod, clock));
        bucket.refill();
        return bucket.consume();
    }

    // Main method for testing
    public static void main(String[] args) throws InterruptedException {
        Clock clock = Clock.systemUTC();
        TokenBucketRateLimiter limiter = new TokenBucketRateLimiter(5, Duration.ofSeconds(1), 5, clock);
        String userId = "user1";

        System.out.println("Starting token bucket test");
        for (int i = 0; i < 10; i++) {
            if (limiter.allowed(userId)) {
                System.out.println("Request allowed at iteration " + i);
            } else {
                System.out.println("Request blocked at iteration " + i);
            }
            Thread.sleep(150); // sleep 150 ms between requests
        }
    }
}



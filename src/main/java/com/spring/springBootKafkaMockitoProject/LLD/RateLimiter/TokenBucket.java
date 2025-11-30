package com.spring.springBootKafkaMockitoProject.LLD.RateLimiter;

import java.time.Clock;
import java.time.Duration;

public class TokenBucket {
    private long refillTimestamp; // timestamp of the last refill
    private long tokenCount;      // current number of tokens in the bucket
    private final int capacity;
    private final Duration period;
    private final int tokensPerPeriod;
    private final Clock clock;

    public TokenBucket(long refillTimestamp, long tokenCount, int capacity, Duration period, int tokensPerPeriod, Clock clock) {
        this.refillTimestamp = refillTimestamp;
        this.tokenCount = tokenCount;
        this.capacity = capacity;
        this.period = period;
        this.tokensPerPeriod = tokensPerPeriod;
        this.clock = clock;
    }

    public void refill() {
        long now = clock.millis();
        long elapsedTime = now - refillTimestamp;
        long elapsedPeriods = elapsedTime / period.toMillis();
        long availableTokens = elapsedPeriods * tokensPerPeriod;
        tokenCount = Math.min(tokenCount + availableTokens, capacity);
        refillTimestamp += elapsedPeriods * period.toMillis();
    }

    public boolean consume() {
        if (tokenCount > 0) {
            --tokenCount;
            return true;
        } else {
            return false;
        }
    }
}
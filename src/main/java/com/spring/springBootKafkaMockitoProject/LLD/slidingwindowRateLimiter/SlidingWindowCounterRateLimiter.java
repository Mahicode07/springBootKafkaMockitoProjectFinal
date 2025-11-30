package com.spring.springBootKafkaMockitoProject.LLD.slidingwindowRateLimiter;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;

public class SlidingWindowCounterRateLimiter {

    private final int maxRequests;
    private final long windowSizeInMillis;
    private final long bucketSizeInMillis;
    private final int numberOfBuckets;
    private final Clock clock;
    private final Map<String, int[]> userRequestBuckets = new HashMap<>();
    private final Map<String, Long> userLastAccessTime = new HashMap<>();

    public SlidingWindowCounterRateLimiter(int maxRequests, long windowSizeInMillis, int numberOfBuckets, Clock clock) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
        this.numberOfBuckets = numberOfBuckets;
        this.bucketSizeInMillis = windowSizeInMillis / numberOfBuckets;
        this.clock = clock;
    }

    public synchronized boolean allowRequest(String userId) {
        long currentTime = clock.millis();
        int[] buckets = userRequestBuckets.computeIfAbsent(userId, k -> new int[numberOfBuckets]);
        long lastAccess = userLastAccessTime.getOrDefault(userId, 0L);

        // Calculate how many buckets have passed since last access
        long elapsedBuckets = (currentTime - lastAccess) / bucketSizeInMillis;

        if (elapsedBuckets >= numberOfBuckets) {
            // Reset all buckets if enough time has passed
            for (int i = 0; i < numberOfBuckets; i++) {
                buckets[i] = 0;
            }
        } else if (elapsedBuckets > 0) {
            // Reset passed buckets
            for (int i = 0; i < elapsedBuckets; i++) {
                int index = (int) ((lastAccess / bucketSizeInMillis + i + 1) % numberOfBuckets);
                buckets[index] = 0;
            }
        }

        // Calculate current bucket index
        int currentBucketIndex = (int) ((currentTime / bucketSizeInMillis) % numberOfBuckets);

        // Calculate total count in the sliding window
        int totalCount = 0;
        for (int count : buckets) {
            totalCount += count;
        }

        // Check if adding this request exceeds maxRequests
        if (totalCount >= maxRequests) {
            return false; // Reject request
        } else {
            // Accept request and increment bucket count
            buckets[currentBucketIndex]++;
            userLastAccessTime.put(userId, currentTime);
            return true;
        }
    }

    // Test method to demonstrate
    public static void main(String[] args) throws InterruptedException {
        Clock clock = Clock.systemUTC();
        int maxRequests = 5;
        long windowSizeInMillis = 1000; // 1 second window
        int numberOfBuckets = 5; // split window into 5 buckets (200ms each)

        SlidingWindowCounterRateLimiter limiter = new SlidingWindowCounterRateLimiter(maxRequests, windowSizeInMillis, numberOfBuckets, clock);

        String user = "user1";

        System.out.println("Sliding Window Counter Rate Limiter test:");
        for (int i = 0; i < 10; i++) {
            if (limiter.allowRequest(user)) {
                System.out.println("Request allowed at iteration " + i);
            } else {
                System.out.println("Request blocked at iteration " + i);
            }
            Thread.sleep(150); // Sleep 150 ms between requests
        }
    }
}

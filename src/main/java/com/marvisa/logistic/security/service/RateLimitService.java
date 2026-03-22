package com.marvisa.logistic.security.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {

    private static final int MAX_REQUESTS = 10;
    private static final long WINDOW_SECONDS = 60;

    private final Map<String, Counter> store = new ConcurrentHashMap<>();

    public boolean allow(String key) {
        long now = Instant.now().getEpochSecond();
        Counter counter = store.computeIfAbsent(key, k -> new Counter(0, now));

        synchronized (counter) {
            if (now - counter.windowStart >= WINDOW_SECONDS) {
                counter.windowStart = now;
                counter.count = 0;
            }

            counter.count++;
            return counter.count <= MAX_REQUESTS;
        }
    }

    private static class Counter {
        int count;
        long windowStart;

        Counter(int count, long windowStart) {
            this.count = count;
            this.windowStart = windowStart;
        }
    }
}
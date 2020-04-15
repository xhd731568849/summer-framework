package com.summer.demos.ratelimiter;

import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreRateLimiter extends RateLimiter implements PermitSupply {

    private final AtomicInteger used = new AtomicInteger(0);
    ;

    public SemaphoreRateLimiter(int threshold) {
        super(threshold);
    }

    @Override
    public void supply(int permits) {
        used.addAndGet(-permits);
    }

    @Override
    protected boolean fastPermit(int permits) {
        if (used.addAndGet(permits) > threshold) {
            supply(permits);
            return false;
        }
        return true;
    }

    @Override
    protected long predict(int permits) {
        return -1;
    }
}

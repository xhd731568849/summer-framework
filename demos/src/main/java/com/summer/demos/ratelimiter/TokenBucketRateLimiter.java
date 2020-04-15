package com.summer.demos.ratelimiter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucketRateLimiter extends RateLimiter {

    private volatile long rateInNanos;
    private AtomicLong suppliedTime = new AtomicLong(System.nanoTime());
    private AtomicInteger storedTokens = new AtomicInteger(0);

    public TokenBucketRateLimiter(int qps) {
        super(qps);
    }

    @Override
    protected boolean fastPermit(int permits) {
        supply();
        if (storedTokens.addAndGet(-permits) >= 0) {
            return true;
        }
        storedTokens.getAndAdd(permits);
        return false;
    }

    private void supply() {
        long lastTime = suppliedTime.get();
        long now = System.nanoTime();
        if (now < lastTime)
            return;

        int permits = (int) ((now - lastTime) / rateInNanos);
        int current = storedTokens.get();
        permits = permits + current > threshold ? threshold - current : permits;
        if (permits > 0 && suppliedTime.compareAndSet(lastTime, now)) {
            current = storedTokens.addAndGet(permits);

            if (current > threshold)
                storedTokens.addAndGet(threshold - current);
        }
    }

    @Override
    protected long predict(int permits) {
        int current = storedTokens.get();
        if (permits <= current)
            return 0;
        int waitTokens = permits - current;
        suppliedTime.set(suppliedTime.get() + waitTokens * rateInNanos);
        return Math.max(0, suppliedTime.get() - System.nanoTime());
    }

    @Override
    public void setLimitThreshold(int threshold) {
        super.setLimitThreshold(threshold);
        if (threshold == 0)
            rateInNanos = 0;
        else
            rateInNanos = TimeUnit.SECONDS.toNanos(1L) / threshold;
    }
}

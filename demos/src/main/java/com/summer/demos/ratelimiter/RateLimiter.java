package com.summer.demos.ratelimiter;

import lombok.Getter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RateLimiter {


    private final Lock locker = new ReentrantLock(true);

    protected @Getter
    volatile int threshold;

    public RateLimiter(int threshold) {
        setLimitThreshold(threshold);
    }

    public void setLimitThreshold(int threshold) {
        if (threshold < 0)
            System.out.println("ratelimiter threshold set to " + threshold + ", is less than zero, will have no effect.");
        else
            this.threshold = threshold;
    }


    public long acquire() {
        return acquire(1);
    }


    public long acquire(int permits) {
        long start = System.nanoTime();
        tryAcquire(permits, Integer.MAX_VALUE, TimeUnit.SECONDS);
        return TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - start);
    }

    public boolean tryAcquire(long timeout, TimeUnit unit) {
        return tryAcquire(1, timeout, unit);
    }

    public boolean tryAcquire(int permits) {
        return tryAcquire(permits, 0, TimeUnit.SECONDS);
    }

    public boolean tryAcquire() {
        return tryAcquire(1, 0, TimeUnit.SECONDS);
    }

    public boolean tryAcquire(int permits, long timeout, TimeUnit unit) {
        if (permits < 0)
            throw new RuntimeException("permits should not less than zero, but found " + permits);
        if (timeout < 0)
            throw new RuntimeException("timeout should not less than zero, but found " + timeout);
        if (permits == 0)
            return true;

        long start = System.nanoTime();
        long timeoutNanos = unit.toNanos(timeout);

        if (threshold == 0)
            return false;

        boolean success = fastPermit(permits);

        if (timeoutNanos > 0)
            timeoutNanos -= System.nanoTime() - start;

        if (success || timeoutNanos <= 0)
            return success;

        start = System.nanoTime();

        boolean locked = false;

        try {

            locked = locker.tryLock(timeoutNanos, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
        if (!locked)
            return false;
        long predict = 0;

        try {
            predict = predict(permits);
            if (predict < 0)
                return false;

            if (predict == 0 || (System.nanoTime() + predict) <= (timeoutNanos + start)) {
                success = true;
            }
        } finally {
            locker.unlock();
        }

        if (success && predict > 0)
            try {
                TimeUnit.NANOSECONDS.sleep(predict);
            } catch (InterruptedException e) {
                System.out.println("rate limiter is interrupted by others, if anything is wrong, check here whether it is a mistake");
            }

        return success;
    }

    protected abstract boolean fastPermit(int permits);


    protected abstract long predict(int permits);

}

package com.summer.demos.ratelimiter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * .
 * .
 *
 * @author xhd
 * @date 2019-08-05
 */
public class Test1 {
    public static void main(String[] args) {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final RateLimiter tokenLimiter = new TokenBucketRateLimiter(1);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 3; i++) {
            executorService.execute(() -> {
                tokenLimiter.acquire();
                LocalDateTime localDateTime = LocalDateTime.now();
                String format = localDateTime.format(dateTimeFormatter);
                System.out.println(Thread.currentThread() + format);
//                try {
//                    Thread.sleep(2000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            });
        }

    }
}

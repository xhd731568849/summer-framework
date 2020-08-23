package com.summer.demos.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.summer.demos.Person;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 学习谷歌的cache
 *
 */
public class GuavaCacheDemo {

    public static void main(String[] args) throws Exception {
        Cache<Long, Person> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(Duration.ofNanos(1L))
                .maximumSize(25)
                .removalListener((x)->{
                    System.out.println(Thread.currentThread().getName() + " my listener start!");
                    System.out.println("x:"+x.wasEvicted());
                    System.out.println("key : "+x.getKey());
                    System.out.println("value : " + x.getValue());
                    System.out.println("causeName : " + x.getCause().name());
                    System.out.println();
                })
                .build();
        putRandomPersonIntoCache(cache);
        putRandomPersonIntoCache(cache);
        putRandomPersonIntoCache(cache);
        putRandomPersonIntoCache(cache);

    }

    public static void putRandomPersonIntoCache(Cache cache){
        Person p1 = getRandomPerson();
        cache.put(p1.getId(),p1);
    }

    static Long id = 1L;
    public static Person getRandomPerson(){
        Person p = new Person();
        p.setId(id++);
        p.setName(UUID.randomUUID().toString());
        return p;
    }
}



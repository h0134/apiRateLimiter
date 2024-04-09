package com.example.UserService.UserService.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.exceptions.JedisException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimit {

    private final RedisTemplate<String, Long> redisTemplate;
    private final Jedis jedis;
    private final Lock lock = new ReentrantLock();

    private final int DEFAULT_DURATION = 60;

    public RateLimit(RedisTemplate<String, Long> redisTemplate, Jedis jedis) {
        this.redisTemplate = redisTemplate;
        this.jedis = jedis;
    }


    public boolean isAllowed(String userId, int maxRequests, int durationInSeconds) {
        String redisKey = "rate_limit:" + userId;
        boolean allowed = false;

        try {
            lock.lock();
            int currentCount = jedis.exists(redisKey) ? Integer.parseInt(jedis.get(redisKey)) : 0;

            if (currentCount < maxRequests) {
                jedis.incr(redisKey);
                if (durationInSeconds > 0) {
                    jedis.expire(redisKey, durationInSeconds);
                } else {
                    jedis.expire(redisKey, DEFAULT_DURATION);
                }

                System.out.println("Request allowed for user " + userId + ". Updated count: " + (currentCount + 1));
                allowed = true;
            } else {
                System.out.println("Rate limit exceeded for user " + userId);
            }
        } catch (JedisException e) {
            System.err.println("Error in Redis operation: " + e.getMessage());
        } finally {
            lock.unlock();
        }

        return allowed;
    }



}




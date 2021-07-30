package com.yinwei.restfulapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * create by: yinwei
 * description: TODO redis的service类
 * create time: 2021/7/23 13:07
 */
@Transactional
@Service
public class RedisService {
    @Autowired
    private org.springframework.data.redis.core.RedisTemplate<String,Object> redisTemplate;

    public void set(String key, Object value,long time) {
        redisTemplate.opsForValue().set(key, value,time,TimeUnit.SECONDS);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean expire(String key, long time) {
        return redisTemplate.expire(key,time, TimeUnit.SECONDS);
    }

    public void remove(String key) {
        redisTemplate.delete(key);
    }

    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key,delta);
    }

    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }
}

package com.ufm.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.ufm.common.service.ICacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CacheServiceImpl
 * @Description 缓存服务
 * @Author 胡蓝天
 * @Date 2019/9/16 2:15 下午
 * @Version 1.0
 */
@Service
@Slf4j
@ConditionalOnClass(RedisOperations.class)
public class CacheServiceImpl implements ICacheService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String getStringCache(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T getObectCache(String key, Class<T> tClass) {
        String jsonString = getStringCache(key);
        if (StringUtils.isEmpty(jsonString)){
            return null;
        }
        return JSON.parseObject(jsonString,tClass);
    }

    @Override
    public Long getExpire(String key) {
        return getExpire(key,TimeUnit.SECONDS);
    }

    @Override
    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key,timeUnit);
    }

    @Override
    public void putCache(String key, Object value) {
        putCache(key,value,DEFAULT_CACHE_TTL_SECOND,TimeUnit.SECONDS);
    }

    @Override
    public void putCache(String key, Object value, long ttl) {
        putCache(key,value,ttl,TimeUnit.SECONDS);
    }

    @Override
    public void putCache(String key, Object value, long ttl, TimeUnit timeUnit) {
        if (value != null) {
            redisTemplate.opsForValue().set(key, JSON.toJSONString(value), ttl, timeUnit);
        }
    }

    @Override
    public long deleteCache(String... key) {
        return deleteCache(CollectionUtils.arrayToList(key));
    }

    @Override
    public long deleteCache(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }

    @Override
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Set<String> keys(String keyPattern) {
        return redisTemplate.keys(keyPattern);
    }

    @Override
    public String serializeKey(String key, Object... format) {
        return String.format(key,format);
    }
}

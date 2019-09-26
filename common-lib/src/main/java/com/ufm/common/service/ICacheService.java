package com.ufm.common.service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ICacheService
 * @Description 缓存对象
 * @Author 胡蓝天
 * @Date 2019/9/16 2:04 下午
 * @Version 1.0
 */
public interface ICacheService {

    /**
     * 默认缓存时间
     */
    int DEFAULT_CACHE_TTL_SECOND = 600;

    /**
     * 获取字符类型缓存数据
     * @param key
     * @return
     */
    String getStringCache(String key);

    /**
     * 获取指定对象类型缓存数据
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    <T>T getObectCache(String key,Class<T> tClass);

    /**
     * 获取缓存剩余时间（秒）
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 获取缓存剩余时间
     * @param key
     * @param timeUnit
     * @return
     */
    Long getExpire(String key, TimeUnit timeUnit);

    /**
     * 放入缓存数据
     * @param key
     * @param value
     */
    void putCache(String key,Object value);

    /**
     * 放入缓存并指定缓存时间
     * @param key
     * @param value
     * @param ttl
     */
    void putCache(String key,Object value,long ttl);

    /**
     * 放入缓存并指定缓存时间及时间单位
     * @param key
     * @param value
     * @param ttl
     * @param timeUnit
     */
    void putCache(String key,Object value,long ttl,TimeUnit timeUnit);

    /**
     * 删除缓存,可传入多个
     * @param key
     */
    long deleteCache(String ... key);

    /**
     * 删除指定集合的缓存数据
     * @param keys
     */
    long deleteCache(Collection<String> keys);

    /**
     * 判断缓存key是否存在
     * @param key
     * @return
     */
    boolean hasKey(String key);

    /**
     * 根据缓存key模式或有所有匹配的key
     * @param keyPattern
     * @return
     */
    Set<String> keys(String keyPattern);

    /**
     * 转换key，加入自定义前缀
     */
    String serializeKey(String key,Object ... format);

}

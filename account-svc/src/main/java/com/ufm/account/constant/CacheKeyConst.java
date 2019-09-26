package com.ufm.account.constant;

/**
 * @ClassName CacheConst
 * @Description 缓存Key静态常量
 * @Author 胡蓝天
 * @Date 2019/9/16 18:01
 * @Version 1.0
 */
public interface CacheKeyConst {

    String AUTH_ACCESS_TOKEN = "account:auth:accessToken:%s";

    String AUTH_REFRESH_TOKEN = "account:auth:refreshToken:%s";

    String AUTH_AUTHORITY = "account:auth:authority:userId-%s:clientId-%s";

}

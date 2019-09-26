package com.ufm.account.service;

import com.ufm.account.pojo.vo.auth.AuthAccessToken;

/**
 * @ClassName IAuthService
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/9/16 1:52 下午
 * @Version 1.0
 */
public interface IAuthService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @param clientId 客户端类型
     * @return
     */
    AuthAccessToken login(String username,String password,String clientId);

}

package com.ufm.account.pojo.vo.auth;

import com.ufm.common.api.BaseModel;
import lombok.*;

/**
 * @ClassName AuthAccessTokenVo
 * @Description 登录令牌信息
 * @Author 胡蓝天
 * @Date 2019/9/16 2:00 下午
 * @Version 1.0
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
public class AuthAccessToken extends BaseModel {

    /**
     * 登录令牌
     */
    private String accessToken;

    /**
     * 剩余失效时间（单位秒）
     */
    private long expiresIn;

    /**
     * 刷新令牌对象
     */
    private String refreshToken;

    /**
     * 客户端ID
     */
    private String clientId;

}

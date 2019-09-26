package com.ufm.account.pojo.vo.auth;

import com.ufm.common.api.BaseModel;
import lombok.*;

/**
 * @ClassName AuthRefreshToken
 * @Description 刷新令牌信息
 * @Author 胡蓝天
 * @Date 2019/9/16 2:01 下午
 * @Version 1.0
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
public class AuthRefreshToken extends BaseModel {

    /**
     * 刷新令牌
     */
    private String refreshToken;

    /**
     * 剩余失效时间（单位秒）
     */
    private long expiresIn;

    /**
     * 客户端ID
     */
    private String clientId;
}

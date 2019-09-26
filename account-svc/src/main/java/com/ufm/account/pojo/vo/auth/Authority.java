package com.ufm.account.pojo.vo.auth;

import com.ufm.account.pojo.vo.UserVo;
import com.ufm.common.api.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName Authority
 * @Description 权限归属对象
 * @Author 胡蓝天
 * @Date 2019/9/16 2:01 下午
 * @Version 1.0
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
public class Authority extends BaseModel {

    /**
     * 登录令牌
     */
    private String accessToken;


    /**
     * 刷新令牌对象
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

    /**
     * 用户信息
     */
    private UserVo userVo;

    /**
     * 用户拥有的角色列表
     */
    private List<String> roleList;

}

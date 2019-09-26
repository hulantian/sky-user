package com.ufm.account.pojo.dto.auth;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName LoginWithUserNameAndPasswordDto
 * @Description 根据用户名密码登录数据传输对象
 * @Author 胡蓝天
 * @Date 2019/9/16 10:14 上午
 * @Version 1.0
 */
@Setter
@Getter
public class LoginWithUserNameAndPasswordDto extends BaseModel {

     /**
     * 用户名
     */
    @NotBlank(message = "用户名为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码为空")
    private String password;

    /**
     * 客户端Id
     */
    private String clientId = "default";

}

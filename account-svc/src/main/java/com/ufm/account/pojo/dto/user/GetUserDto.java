package com.ufm.account.pojo.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName GetUserDto
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/8/29 5:21 下午
 * @Version 1.0
 */
@Setter
@Getter
public class GetUserDto {

    @NotBlank(message = "用户Id不能为空")
    private String userId;

    private String username;

    @NotBlank(message = "手机号码不能为空")
    private String phone;

    @NotBlank
    private String orgId;

    @NotBlank
    private String rootOrgId;
}

package com.ufm.account.pojo.dto.role;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName GetUserRoleListDto
 * @Description 获取用户的角色列表
 * @Author 胡蓝天
 * @Date 2019-09-17 14:27
 * @Version 1.0
 */
@Getter
@Setter
public class GetRoleListByUserIdDto {

    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空")
    private String userId;
}

package com.ufm.account.pojo.dto.role;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName AddRoleToUserDto
 * @Description 给用户添加角色
 * @Author 胡蓝天
 * @Date 2019-09-17 10:46
 * @Version 1.0
 */
@Getter
@Setter
public class AddRoleToUserDto {

    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不能为空")
    private String userId;

    /**
     * 角色代码
     */
    @NotBlank(message = "角色代码不能为空")
    @Size(max = 32, message = "角色代码不能超过32位")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "角色只能为数字或字母")
    private String roleCode;
}

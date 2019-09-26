package com.ufm.account.pojo.dto.role;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @ClassName AddRoleDto
 * @Description 添加角色
 * @Author 胡蓝天
 * @Date 2019-09-16 13:53
 * @Version 1.0
 */
@Getter
@Setter
public class AddRoleDto extends BaseModel {

    /**
     * 角色代码
     */
    @NotBlank(message = "角色代码不能为空")
    @Size(max = 32, message = "角色代码不能超过32位")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "角色只能为数字或字母")
    private String code;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名字不能为空")
    @Size(max = 64, message = "角色名字不能超过64位")
    private String name;

    /**
     * 角色所在系统编码
     */
    @NotBlank(message = "角色所在系统编码不能为空")
    private String sysCode;

    /**
     * 角色描述
     */
    @Size(max = 128, message = "角色描述不能超过64位")
    private String description;
}

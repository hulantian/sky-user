package com.ufm.account.pojo.dto.user;

import com.ufm.common.api.BaseModel;
import com.ufm.common.validation.PhoneNumber;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName AddUserDto
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/8/29 3:26 下午
 * @Version 1.0
 */
@Setter
@Getter
public class AddUserDto extends BaseModel {

    /**
     * 用户名称
     */
    @NotBlank
    private String username;

    /**
     * 手机号
     */
    @PhoneNumber
    private String phone;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 机构id
     */
    private String orgId;

    /**
     * 机构root_org_id
     */
    private String rootOrgId;
}

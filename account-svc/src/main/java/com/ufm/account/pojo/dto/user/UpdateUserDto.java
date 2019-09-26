package com.ufm.account.pojo.dto.user;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @Author 胡蓝天
 * @Date 2019/9/23 17:21
 */
@Setter
@Getter
public class UpdateUserDto extends BaseModel {

    @NotBlank(message = "用户ID不能为空")
    private String userId;

    private String username;

    private String phone;

    //状态
    private int status = 1;

}

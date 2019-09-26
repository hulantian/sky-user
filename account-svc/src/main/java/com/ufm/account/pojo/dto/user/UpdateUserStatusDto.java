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
public class UpdateUserStatusDto extends BaseModel {
    //状态
    @NotBlank(message = "用户状态不能为空")
    private int status;

    @NotBlank(message = "用户id不能为空")
    private String userId;

}

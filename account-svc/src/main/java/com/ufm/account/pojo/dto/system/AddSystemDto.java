package com.ufm.account.pojo.dto.system;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


/**
 * 添加系统信息传入信息对象
 * @Author 胡蓝天
 * @date 2019-09-24 15:14
 * @version 1.0
 */
@Setter
@Getter
public class AddSystemDto extends BaseModel {
    /**
     * 系统编码
     */
    @NotBlank(message = "系统编码不能为空")
    private String code;

    /**
     * 系统名称
     */
    @NotBlank(message = "系统名称不能为空")
    private String name;

    /**
     * 系统描述
     */
    private String description;

}

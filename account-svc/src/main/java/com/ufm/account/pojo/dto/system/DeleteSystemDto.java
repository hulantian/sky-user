package com.ufm.account.pojo.dto.system;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


/**
 * 删除系统信息传入信息
 * @Author 胡蓝天
 * @date 2019-09-24 15:14
 * @version 1.0
 */
@Setter
@Getter
public class DeleteSystemDto extends BaseModel {
    /**
     * 系统编码
     */
    @NotBlank(message = "系统编码不能为空")
    private String code;

}

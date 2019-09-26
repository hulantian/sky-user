package com.ufm.account.pojo.dto.organ;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName UpdateOrganDto
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/9/11:54
 * @Version 1.0
 */
@Setter
@Getter
public class UpdateOrganDto extends BaseModel {

    /**
     * 组织Id
     */
    @NotBlank(message = "组织Id不能为空")
    private String organId;
    /**
     * 组织名称
     */
    @NotBlank
    private String name;

    /**
     * 父组织Id
     */
    @NotBlank(message = "父组织Id不能为空")
    private String pid;

    /**
     * 状态
     */
    private int status;

}

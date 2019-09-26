package com.ufm.account.pojo.dto.organ;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName GetOrganDto
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/9/11 5:21 下午
 * @Version 1.0
 */
@Setter
@Getter
public class GetOrganDto extends BaseModel {

    @NotBlank(message = "组织Id不能为空")
    private String organId;

    public GetOrganDto() {
    }

    public GetOrganDto(@NotBlank(message = "组织Id不能为空") String organId) {
        this.organId = organId;
    }
}

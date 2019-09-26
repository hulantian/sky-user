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
public class GetOrganListDto extends BaseModel {

    private String pid;
}

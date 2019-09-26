package com.ufm.account.pojo.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author 胡蓝天
 * @Date 2019/9/23 17:21
 */
@Setter
@Getter
public class GetUserListDto {

    @NotBlank(message = "用户Id不能为空")
    private List<String> userId;

}

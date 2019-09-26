package com.ufm.account.pojo.vo.system;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 系统信息返回数据
 * @Author 胡蓝天
 * @date 2019-09-17 13:40
 * @version 1.0
 */
@Getter
@Setter
public class SystemVo extends BaseModel {
    /**
     * 系统编码
     */
    private String code;

    /**
     *系统名称
     */
    private String name;

    /**
     * 系统描述
     */
    private String description;

}

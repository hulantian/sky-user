package com.ufm.account.pojo.vo.role;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName RoleVo
 * @Description 角色对象
 * @Author 胡蓝天
 * @Date 2019-09-17 11:51
 * @Version 1.0
 */
@Getter
@Setter
public class RoleVo extends BaseModel {

    /**
     * 角色代码
     */
    private String code;

    /**
     * 角色名字
     */
    private String name;

    /**
     * 所属系统编码
     */
    private String sysCode;

    /**
     * 角色描述
     */
    private String description;
}

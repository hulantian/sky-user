package com.ufm.account.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName RoleResourceEntity
 * @Description 角色资源关系
 * @Author 胡蓝天
 * @Date 2019/9/3 3:07 下午
 * @Version 1.0
 */
@Getter
@Setter
@TableName("t_role_resource")
public class RoleResourceEntity {

    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 资源Id
     */
    @TableField("res_id")
    private String resId;
}

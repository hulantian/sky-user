package com.ufm.account.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName UserRoleEntity
 * @Description 用户角色关系类
 * @Author 胡蓝天
 * @Date 2019/9/3 3:05 下午
 * @Version 1.0
 */
@Getter
@Setter
@TableName("t_user_role")
public class UserRoleEntity {

    @TableField("user_id")
    private String userId;

    @TableField("role_code")
    private String roleCode;
}

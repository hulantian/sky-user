package com.ufm.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName UserEntity
 * @Description 用户实体类
 * @Author 胡蓝天
 * @Date 2019/8/29 3:17 下午
 * @Version 1.0
 */
@Getter
@Setter
@TableName("t_user")
public class UserEntity extends BaseEntity {

    /**
     * 用户id 雪花算法获取主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 所在机构Id
     */
    @TableField("org_id")
    private String orgId;

    /**
     * 所在主机构Id
     */
    @TableField("root_org_id")
    private String rootOrgId;

    /**
     * 用户名称
     */
    @TableField
    private String username;

    /**
     * 密码
     */
    @TableField
    private String password;

    /**
     * 电话号码
     */
    @TableField
    private String phone;

    /**
     * 电子邮件
     */
    @TableField
    private String email;

    /**
     * 用户状态 1：正常，2：停用
     */
    @TableField
    private int status = 1;


}

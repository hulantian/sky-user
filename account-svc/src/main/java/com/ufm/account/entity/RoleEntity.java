package com.ufm.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName RoleEntity
 * @Description 角色实体对象
 * @Author 胡蓝天
 * @Date 2019/9/3 2:58 下午
 * @Version 1.0
 */
@Setter
@Getter
@TableName("t_role")
public class RoleEntity extends BaseEntity {

    /**
     * 角色id 雪花算法获取主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    @TableField("code")
    private String code;

    @TableField("name")
    private String name;

    @TableField("sys_code")
    private String sysCode;

    @TableField("description")
    private String description;

}

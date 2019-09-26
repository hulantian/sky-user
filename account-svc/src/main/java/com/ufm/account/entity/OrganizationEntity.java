package com.ufm.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName OrganizationEntity
 * @Description 组织机构实体
 * @Author 胡蓝天
 * @Date 2019/9/3 2:41 下午
 * @Version 1.0
 */
@Setter
@Getter
@TableName("t_organization")
public class OrganizationEntity extends BaseEntity{

    /**
     * 主键（机构ID）
     */
    @TableId(value = "id",type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 父id
     */
    @TableField("pid")
    private String pid;

    /**
     * 机构名称
     */
    @TableField("name")
    private String name;

    /**
     * 机构状态 1：正常 2停用
     */
    @TableField("status")
    private int status = 1;

}

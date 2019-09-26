package com.ufm.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName ResourceEntity
 * @Description 资源（功能）信息实体
 * @Author 胡蓝天
 * @Date 2019/9/3 3:02 下午
 * @Version 1.0
 */
@Setter
@Getter
@TableName("t_resource")
public class ResourceEntity extends BaseEntity {

    /**
     * 主键（资源Id）
     */
    @TableId(value = "org_id",type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 父级资源Id
     */
    @TableField("pid")
    private String pid;

    /**
     * 资源名称
     */
    @TableField("name")
    private String name;

    /**
     * 访问地址
     */
    @TableField("url")
    private String url;

    /**
     * 是否为功能模块：1：功能模块，2：功能文件夹
     */
    @TableField("is_res")
    private int isRes = 1;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;
}

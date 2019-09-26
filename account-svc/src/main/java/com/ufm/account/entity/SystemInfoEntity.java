package com.ufm.account.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName SystemInfoEntity
 * @Description 系统信息实体
 * @Author 胡蓝天
 * @Date 2019/9/3 2:52 下午
 * @Version 1.0
 */
@Setter
@Getter
@TableName("t_system_info")
public class SystemInfoEntity extends BaseEntity{

    /**
     * 系统id 雪花算法获取主键
     */
    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * 系统编码
     */
    @TableField("code")
    private String code;

    /**
     * 系统名称
     */
    @TableField("name")
    private String name;

    /**
     * 系统描述
     */
    @TableField("description")
    private String description;

}

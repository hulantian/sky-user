package com.ufm.account.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @ClassName BaseEntity
 * @Description 基础实体
 * @Author 胡蓝天
 * @Date 2019/9/3 2:48 下午
 * @Version 1.0
 */
@Setter
@Getter
public class BaseEntity extends BaseModel {

    /**
     * 是否已删除
     */
    @TableField("is_delete")
    private int isDelete = 1;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime updateTime;
}

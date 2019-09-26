package com.ufm.account.pojo.vo;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @ClassName OrganVo
 * @Description 组织对象
 * @Author 胡蓝天
 * @Date 2019/9/11 3:24 下午
 * @Version 1.0
 */
@Getter
@Setter
public class OrganVo extends BaseModel {

    /**
     * 组织Id
     */
    private String id;

    /**
     * 父组织Id
     */
    private String pid;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 组织状态
     */
    private int status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 上次修改时间
     */
    private LocalDateTime updateTime;
}

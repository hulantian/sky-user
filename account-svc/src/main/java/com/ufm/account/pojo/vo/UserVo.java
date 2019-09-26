package com.ufm.account.pojo.vo;

import com.ufm.common.api.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @ClassName UserVo
 * @Description 用户对象
 * @Author 胡蓝天
 * @Date 2019/8/29 3:24 下午
 * @Version 1.0
 */
@Getter
@Setter
public class UserVo extends BaseModel {

    /**
     * 用户Id
     */
    private String id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 状态
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

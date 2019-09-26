package com.ufm.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName CodeConstant
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/8/29 2:23 下午
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum  CodeConstant implements ICode{

    /**
     * 公共错误码清单
     */
    SUCCESS_RESPONSE("0","操作成功！"),
    SERVER_ERROR("5000","服务器开小差"),
    MISSING_REQUEST_PARAMETER("Missing Required Parameter","缺少请求参数: %s"),
    METHOD_ARGUMENT_TYPE_MISMATCH("Method Argument Type Mismatch","请求参数验证失败，%s:%s"),

    /**
     * 系统模块错误码
     */
    SYSTEM_EXISTS("7000", "该系统已经存在"),
    SYSTEM_NOT_EXISTS("7001", "该系统不存在"),
    ;


    private String code;

    private String msg;


}

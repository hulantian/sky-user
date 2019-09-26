package com.ufm.account.enums;

import com.ufm.common.code.ICode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode implements ICode {

    /**
     * 权限相关错误码，暂定100
     */

    AUTH_USER_NOT_FOUND("100404","用户不存在"),
    AUTH_USER_STOPED("100101","用户被锁定"),
    AUTH_USER_PASSWORD_NOT_MATCHED("100102","用户密码不匹配"),

    /**
     * 用户错误码
     */

    USERPHONE_EXIST("200001","手机号码已存在"),
    USER_UNFOUND("200002","用户不存在"),
    USER_DELETE("200003","用户已被删除"),
    USERNAMWE_XIST("200004","用户名已存在"),
    USER_ERROR_PARAM("200005","非法参数"),

    /**
     * 角色模块的错误码 临时分段 300
     */
    ROLE_EXISTS("300001", "角色代码已经存在"),
    ROLE_NOT_EXISTS("300002", "角色不存在"),
    ROLE_EXISTS_IN_USER("300003", "用户已经属于该角色"),
    ROLE_ALREADY_HAVE_RESOURCE("300004", "角色已经拥有该权限"),

    /**
     * 组织机构相关错误码（400001-400099）
     */
    ORGANNIZATION_EXIST("100001","已存在相同组织"),
    ORGANNIZATION_DELETED("100002","组织已不存在"),
    ORGANNIZATION_EXIST_MEMBER("100003","该组织下有成员，无法删除"),

    ;

    private String code;

    private String msg;

}

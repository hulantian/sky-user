package com.ufm.common.error;

import com.ufm.common.code.CodeConstant;
import com.ufm.common.code.ICode;
import lombok.Data;

import javax.swing.*;

/**
 * @ClassName ServiceExcepion
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/8/29 2:12 下午
 * @Version 1.0
 */
@Data
public class ServiceExcepion extends RuntimeException {

    private String code;

    private String msg;

    public ServiceExcepion(ICode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    public ServiceExcepion( String msg) {
        super(msg);
        this.code = Constant.SERVER_ERROR_CODE;
        this.msg = msg;
    }

    public ServiceExcepion(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


    /**
     * 动态参数
     * @param code
     * @param msgFormater
     */
    public ServiceExcepion(ICode code,String ... msgFormater) {
        super(String.format(code.getMsg(),msgFormater));
        this.code = code.getCode();
        this.msg = String.format(code.getMsg(),msgFormater);
    }
}

package com.ufm.common.api;

import com.ufm.common.code.CodeConstant;
import com.ufm.common.code.ICode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;

/**
 * @ClassName Result
 * @Description 基础响应
 * @Author 胡蓝天
 * @Date 2019/8/29 2:15 下午
 * @Version 1.0
 */
@Setter
@Getter
public class Result<T> extends BaseModel {

    /**
     * 响应编码
     */
    private String code ;

    /**
     * 响应消息
     */
    private String msg ;

    /**
     * 接口响应时间戳
     */
    private long timestamp = System.currentTimeMillis();

    /**
     * 接口响应数据
     */
    private T data;

    private Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 错误码构造方法
     * @param code
     */
    private Result(ICode code){
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    /**
     * 错误响应
     * @param code
     * @return
     */
    public static Result error(ICode code){
        return new Result(code);
    }

    public static Result error(String code, String msg){
        return new Result(code, msg);
    }

    /**
     * 错误响应
     * @param code
     * @param params  错误信息替换,需按位置输入指定格式
     * @return
     */
    public static Result error(ICode code,Object ... params){
        Result result =  new Result(code);
        result.msg = String.format(result.msg,params);
        return result;
    }

    /**
     * 成功默认响应
     * @return
     */
    public static Result success(){
        return new Result(CodeConstant.SUCCESS_RESPONSE);
    }

    /**
     * 成功默认响应并返回数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result(CodeConstant.SUCCESS_RESPONSE);
        result.data = data;
        return result;
    }

    /**
     * 单一数据键值响应
     * @param dataKey
     * @param data
     * @return
     */
    public static Result success(String dataKey, Object data) {
        return success(Collections.singletonMap(dataKey, data));
    }


}

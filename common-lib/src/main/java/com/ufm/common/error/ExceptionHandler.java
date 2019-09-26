package com.ufm.common.error;

import com.ufm.common.api.Result;
import com.ufm.common.code.CodeConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName ExceptionHandler
 * @Description TODO
 * @Author 胡蓝天
 * @Date 2019/8/29 2:29 下午
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    /**
     * 业务服务错误
     * @param e
     * @return
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(ServiceExcepion.class)
    public Result handleError(ServiceExcepion e){
        log.warn("业务服务错误：{}",e);
        return Result.error(e.getCode(), e.getMsg());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleError(MissingServletRequestParameterException e) {
        log.warn("缺少请求参数", e);
        return  Result.error(CodeConstant.MISSING_REQUEST_PARAMETER,e.getParameterName());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleError(MethodArgumentNotValidException e) {
        log.warn("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        return  Result.error(CodeConstant.METHOD_ARGUMENT_TYPE_MISMATCH,error.getField(), error.getDefaultMessage());
    }


}

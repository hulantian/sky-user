package com.ufm.common.validation;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @ClassName Constant
 * @Description 手机号验证
 * @Author 胡蓝天
 * @Date 2019/8/29 2:13 下午
 * @Version 1.0
 */
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "手机号验证不正确";
    Class[] groups() default {};
    Class[] payload() default {};
}

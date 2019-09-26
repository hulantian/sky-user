package com.ufm.common.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName Constant
 * @Description 手机号验证规则逻辑实现
 * @Author 胡蓝天
 * @Date 2019/8/29 2:13 下午
 * @Version 1.0
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext context) {
        if (phoneField == null) {
            return true;
        }
        return  phoneField.matches("[0-9]+") && (phoneField.length() > 8) && (phoneField.length() < 14);
    }
}

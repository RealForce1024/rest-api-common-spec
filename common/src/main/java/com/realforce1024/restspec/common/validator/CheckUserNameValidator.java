package com.realforce1024.restspec.common.validator;

import com.realforce1024.restspec.common.annotation.CheckUserName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
public class CheckUserNameValidator implements ConstraintValidator<CheckUserName, Object> {

    @Override
    public void initialize(CheckUserName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String[] invalid = {"gdx", "奥利给"};
        for (String s : invalid) {
            if (value.toString().toLowerCase().contains(s)) {
                log.warn("用户名非法: '{}'", value);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "gdx";
        System.out.println("xxxgdxbbb".contentEquals(str));
        System.out.println("xxxgdxbbb".contains(str));
    }
}
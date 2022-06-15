package com.realforce1024.restspec.common.annotation;

import com.realforce1024.restspec.common.validator.CheckUserNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author 编程燃风 RealForce1024
 */
@Documented
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckUserNameValidator.class)
public @interface CheckUserName {
    String message() default "用户名不能包含非法字符串gdx|奥利给";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}


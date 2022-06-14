package com.realforce1024.restspec.common.annotation;

import com.realforce1024.restspec.common.validator.CheckUserNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Min;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 编程燃风 RealForce1024
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {CheckUserNameValidator.class})
public @interface CheckUserName {
    String message() default "{用户名不能包含词组gdx或者奥利给}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

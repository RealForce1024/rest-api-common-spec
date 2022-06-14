package com.realforce1024.restspec.common.annotation;

import java.lang.annotation.*;

/**
 * @author 编程燃风 RealForce1024
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoResponseWrapper {
}

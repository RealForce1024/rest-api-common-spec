package com.realforce1024.restspec.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 编程燃风 RealForce1024
 */

@AllArgsConstructor
public enum ResultCodeEnum implements CodeEnum {
    /**
     * 响应成功
     */
    SUCCESS("1000", "成功"),
    /**
     * 服务器异常
     */
    FAIL("5000", "服务器开小差"),
    ;

    private final String code;
    private final String msg;

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String msg() {
        return this.msg;
    }
}

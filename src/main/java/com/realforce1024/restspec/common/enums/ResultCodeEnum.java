package com.realforce1024.restspec.common.enums;

import lombok.AllArgsConstructor;

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
     * 数学异常
     */
    MATH_ERROR("5100", "数学错误"),
    /**
     * 请求方法不支持
     */
    BAD_METHOD("4001", "请求方法不支持"),
    /**
     * 请求媒体类型不支持
     */
    BAD_MEDIA("4002", "请求媒体类型不支持"),
    /**
     * 请求body参数不可读
     */
    BAD_BODY("4003", "请求body参数不可读"),
    /**
     * 路径不存在
     */
    BAD_PATH("4004", "路径不存在"),
    /**
     * 请求参数缺失
     */
    BAD_PARAMETER("4005", "请求参数缺失"),
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

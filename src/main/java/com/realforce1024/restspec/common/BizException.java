package com.realforce1024.restspec.common;

import com.realforce1024.restspec.common.enums.CodeEnum;
import lombok.Data;

/**
 * @author 编程燃风 RealForce1024
 */
@Data
public class BizException extends RuntimeException implements CodeEnum {
    private final String code;
    private final String msg;

    public BizException(CodeEnum codeEnum) {
        this.code = codeEnum.code();
        this.msg = codeEnum.msg();
    }

    @Override
    public String getMessage() {
        return this.msg;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String msg() {
        return this.msg;
    }
}

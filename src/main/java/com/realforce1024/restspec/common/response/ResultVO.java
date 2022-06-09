package com.realforce1024.restspec.common.response;

import com.realforce1024.restspec.common.enums.CodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 编程燃风 RealForce1024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> ResultVO<T> ok(CodeEnum codeEnum, T data) {
        return ok(codeEnum.code(), codeEnum.msg(), data);
    }

    public static <T> ResultVO<T> ok(String code, String msg, T data) {
        return build(code, msg, data);
    }

    private static <T> ResultVO<T> build(String code, String msg, T data) {
        return new ResultVO<>(code, msg, data);
    }
}

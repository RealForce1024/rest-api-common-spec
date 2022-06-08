package com.realforce1024.restspec.common.response;

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

    public ResultVO<T> build(String code, String msg, T data) {
        return new ResultVO<>(code, msg, data);
    }
}

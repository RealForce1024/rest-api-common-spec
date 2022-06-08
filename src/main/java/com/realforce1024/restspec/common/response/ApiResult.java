package com.realforce1024.restspec.common.response;

import lombok.Data;

/**
 * @author RealForce1024
 */
@Deprecated
@Data
public class ApiResult<T> {
    private String code = "1000";
    private String msg = "ok";
    private T data;

    public static <T> ApiResult<T> ok(T data) {
        ApiResult<T> resultVO = new ApiResult<>();
        resultVO.setData(data);
        return resultVO;
    }
}

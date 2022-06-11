package com.realforce1024.restspec.common.response;

import com.realforce1024.restspec.common.enums.CodeEnum;
import com.realforce1024.restspec.common.request.RequestModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * @author 编程燃风 RealForce1024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO<T> {
    private String code;
    private String msg;
    private Long utc8;
    private String reqId;
    private T data;

    public static <T> ResultVO<T> ok(CodeEnum codeEnum, T data) {
        return ok(codeEnum.code(), codeEnum.msg(), data);
    }

    public static <T> ResultVO<T> ok(String code, String msg, T data) {
        return build(code, msg, data);
    }

    private static <T> ResultVO<T> build(String code, String msg, T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(data);
        resultVO.setUtc8(Instant.now().toEpochMilli());
        resultVO.setReqId(RequestModel.get().getReqId());
        return resultVO;
    }

}

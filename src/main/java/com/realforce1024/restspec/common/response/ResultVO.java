package com.realforce1024.restspec.common.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"code", "msg", "reqId", "utc8", "cost", "data"})
public class ResultVO<T> {
    private String code;
    private String msg;
    private T data;
    private String reqId;
    private Long utc8;
    private Long cost;

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
        Long endTime = Instant.now().toEpochMilli();
        resultVO.setUtc8(endTime);
        Long cost = endTime - RequestModel.get().getStartTime();
        resultVO.setCost(cost);
        RequestModel.get().setCost(cost);
        resultVO.setReqId(RequestModel.get().getReqId());
        return resultVO;
    }

}

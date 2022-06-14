package com.realforce1024.restspec.common.request;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

/**
 * @author 编程燃风 RealForce1024
 */
@Data
public class RequestModel {
    private static final ThreadLocal<RequestModel> REQUEST_MODEL = new ThreadLocal<>();
    private String reqId = "ErrReqId:" + UUID.randomUUID().toString().replace("-", "");
    private Long startTime = Instant.now().toEpochMilli();
    private Long cost = 0L;

    public static RequestModel get() {
        return REQUEST_MODEL.get() == null ? new RequestModel() : REQUEST_MODEL.get();
    }

    public static void setRequestModel(RequestModel requestModel) {
        REQUEST_MODEL.set(requestModel);
    }

    public static void remove() {
        REQUEST_MODEL.remove();
    }
}

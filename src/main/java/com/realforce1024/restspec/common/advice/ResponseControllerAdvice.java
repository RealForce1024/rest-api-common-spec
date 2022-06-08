package com.realforce1024.restspec.common.advice;

import com.realforce1024.restspec.common.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
@ControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (!returnType.getParameterType().equals(ResultVO.class)) {
            log.info("判断: 需要结果值封装");
            return true;
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // todo String等特殊类型的处理
        log.info("执行结果值封装");
        // todo 重构ResultVO 重载方法
        return new ResultVO<>().build("1000", "success", body);
    }
}

package com.realforce1024.restspec.common.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realforce1024.restspec.common.annotation.NoResponseWrapper;
import com.realforce1024.restspec.common.enums.ResultCodeEnum;
import com.realforce1024.restspec.common.response.ResultVO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.File;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
@ControllerAdvice
public class ResponseControllerAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        Class<?> type = returnType.getParameterType();
        if (!type.equals(ResultVO.class) && !type.equals(File.class) && !returnType.hasMethodAnnotation(NoResponseWrapper.class)) {
            log.info("判断: 需要结果值封装");
            return true;
        }
        return false;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 方式1：将StringHttpMessageConverter从配置中移除 (不推荐)
        // 方式2: 契合下StringHttpMessageConverter，封装ResultVO，然后将ResultVO进行Json字符串的转换

        ResultVO<Object> resultVO = ResultVO.ok(ResultCodeEnum.SUCCESS, body);
        log.info("resultVO: {}", resultVO);
        if (body instanceof String) {
            log.info("String类型执行包装");
            return new ObjectMapper().writeValueAsString(resultVO);
        }
        log.info("执行结果值封装");
        return resultVO;
    }
}

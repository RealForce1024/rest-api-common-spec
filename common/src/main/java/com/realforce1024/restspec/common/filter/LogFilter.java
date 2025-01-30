package com.realforce1024.restspec.common.filter;


import com.realforce1024.restspec.common.request.RequestModel;
import com.realforce1024.restspec.common.request.RequestWrapper;
import com.realforce1024.restspec.common.utils.HttpUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.*;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
@WebFilter
@Component
@Order(1)
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LogFilter init");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("========================================== Start ==========================================");

        if (shouldSkip(request)) {
            // 不需要记录请求体和响应体 文件上传
            log.info("不需要记录请求体和响应体");
            chain.doFilter(request, response);
            return;
        }

        String reqId = UUID.randomUUID().toString().replace("-", "");
        Long startTime = Instant.now().toEpochMilli();
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        String httpMethod = ((HttpServletRequest) request).getMethod();
        String clientIp = request.getRemoteAddr();
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            params.put(name, request.getParameter(name));
        }


        MDC.put("req_id", reqId);
        MDC.put("req_uri", HttpUtils.assemble(requestURI, params));
        MDC.put("http_method", httpMethod);
        MDC.put("client_ip", clientIp);

        log.info("GET请求参数: {}", params);
        RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
        log.info("Post请求参数: {}", request.getAttribute("body"));

        log.info("reqId: {},startTime: {},reqUri:{}", reqId, startTime, requestURI);

        RequestModel requestModel = new RequestModel();
        requestModel.setReqId(reqId);
        requestModel.setStartTime(startTime);
        // 特别注意，需要手动设置，否则会产生NPE
        RequestModel.setRequestModel(requestModel);
        // int i = 1 / 0;
        chain.doFilter(requestWrapper, response);

        Long cost = RequestModel.get().getCost();
        if (cost > 500) {
            log.warn("响应时间超过500ms: {}", cost);
        }

        log.info("=========================================== End ===========================================");
        log.info("");
        RequestModel.remove();
        MDC.clear();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean shouldSkip(ServletRequest request) {
        String contentType = request.getContentType();
        log.info("MIME类型 contentType: {}", contentType);
        if (request instanceof HttpServletRequest) {
            log.info("过滤multipart/form-data请求类型流消费与包装");
            return contentType != null && contentType.contains("multipart/form-data");
        }

      /*  // 跳过文件上传接口
        if (request.getRequestURI().contains("/upload")) {
            return true;
        }
        // 跳过文件下载接口
        if (request.getRequestURI().contains("/download")) {
            return true;
        }
        // 跳过静态资源
        if (request.getRequestURI().contains("/static")) {
            return true;
        }*/
        return false;
    }
}

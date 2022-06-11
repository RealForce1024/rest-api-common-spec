package com.realforce1024.restspec.common.filter;


import com.realforce1024.restspec.common.request.RequestModel;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

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
        String reqId = UUID.randomUUID().toString().replace("-", "");
        Long startTime = Instant.now().toEpochMilli();
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        String httpMethod = ((HttpServletRequest) request).getMethod();
        String clientIp = request.getRemoteAddr();

        MDC.put("req_id", reqId);
        MDC.put("req_uri", requestURI);
        MDC.put("http_method", httpMethod);
        MDC.put("client_ip", clientIp);


        log.info("reqId: {},startTime: {},reqUri:{}", reqId, startTime, requestURI);

        RequestModel requestModel = new RequestModel();
        requestModel.setReqId(reqId);
        requestModel.setStartTime(startTime);
        // 特别注意，需要手动设置，否则会产生NPE
        RequestModel.setRequestModel(requestModel);
        chain.doFilter(request, response);
        RequestModel.remove();
        MDC.clear();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

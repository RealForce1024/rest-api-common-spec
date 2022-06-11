package com.realforce1024.restspec.common.filter;


import com.realforce1024.restspec.common.request.RequestModel;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;
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
        log.info("startTime: {}", startTime);
        log.info("filter reqId: {}", reqId);
        RequestModel requestModel = new RequestModel();
        requestModel.setReqId(reqId);
        requestModel.setStartTime(startTime);
        // 特别注意，需要手动设置，否则会产生NPE
        RequestModel.setRequestModel(requestModel);
        chain.doFilter(request, response);
        RequestModel.remove();
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

package com.realforce1024.restspec.common.basic;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorController implements ErrorController {
    @RequestMapping
    public void error(HttpServletRequest request) throws Exception {
        // int statusCode = (int) request.getAttribute("jakarta.servlet.error.error_code");
        int statusCode = (int) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        log.info("statusCode: {}", statusCode);
        String errMsg = switch (statusCode) {
            case 401 -> "客户端未认证";
            case 403 -> "客户端被拒绝";
            case 404 -> "访问资源不存在";
            case 405 -> "http方法不正确";
            case 500 -> "服务开小差请稍后再试";
            case 502 -> "502网关错误";
            case 503 -> "503服务不可用,请稍后再试";
            default -> "请检查客户端方法或路径";
        };
        throw new Exception(errMsg);
    }

}
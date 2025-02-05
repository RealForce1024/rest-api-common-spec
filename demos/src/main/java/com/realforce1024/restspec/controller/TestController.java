package com.realforce1024.restspec.controller;

import com.realforce1024.restspec.common.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 编程燃风 RealForce1024
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @GetMapping("/hi")
    public ApiResult<String> sayHi(String username) {
        log.info("username: {}", username);
        return ApiResult.ok("hello " + username);
    }

    @GetMapping("/hey")
    public ApiResult<String> sayHey(String username) {
        log.info("username: {}", username);
        return ApiResult.ok("hello " + username);
    }
}

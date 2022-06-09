package com.realforce1024.restspec.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
// @Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("移除StringHttpMessageConverter");
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof StringHttpMessageConverter);
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }
}

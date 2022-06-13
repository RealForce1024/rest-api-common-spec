package com.realforce1024.restspec.common.config;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 编程燃风 RealForce1024
 */
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // @Override
    // public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    //     log.info("移除StringHttpMessageConverter");
    //     converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof StringHttpMessageConverter);
    //     WebMvcConfigurer.super.configureMessageConverters(converters);
    // }

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                //failFast在多个条件异常触发时，只要出现校验失败的情况，就立即结束校验，不再进行后续的校验。
                .failFast(true)
                .buildValidatorFactory();

        return validatorFactory.getValidator();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator());
        return methodValidationPostProcessor;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/", "/static", "/public");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}

package com.realforce1024.restspec;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class RestApiCommonSpecApplication implements EnvironmentAware {

    private static String ENV;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RestApiCommonSpecApplication.class);
        ConfigurableEnvironment env = app.run(args).getEnvironment();
        log.info("系统启动成功!!! 启用的配置环境变量为: 【{}】 -Service地址: \thttp://127.0.0.1:{}/", ENV, env.getProperty("server.port"));
    }

    @Override
    public void setEnvironment(Environment environment) {
        String[] activeProfiles = environment.getActiveProfiles();
        ENV = 0 == activeProfiles.length ? "default" : activeProfiles[0];
    }
}

package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: lx
 * @Date: Created in 2019/4/14
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CsrfInterceptor()).addPathPatterns("/*").order(1);
        registry.addInterceptor(new LoadSaltInterceptor()).addPathPatterns("/*").order(2);
        super.addInterceptors(registry);
    }
}

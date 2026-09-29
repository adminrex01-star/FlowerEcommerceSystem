package com.flower.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration   // 告诉Spring这是一个配置类
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册登录拦截器（拦截所有请求，排除登录/注册/静态资源）
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register", "/css/**", "/js/**", "/images/**");

        // 注册管理员拦截器（只拦截/admin/**）
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/admin/**");
    }
}
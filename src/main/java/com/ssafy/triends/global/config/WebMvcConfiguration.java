package com.ssafy.triends.global.config;

import com.ssafy.triends.global.interceptor.AuthorizationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@MapperScan(basePackages = {"com.ssafy.triends.**.mapper"})
public class WebMvcConfiguration implements WebMvcConfigurer {

    AuthorizationInterceptor authorizationInterceptor;

    public WebMvcConfiguration(AuthorizationInterceptor authorizationInterceptor) {
        this.authorizationInterceptor = authorizationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.authorizationInterceptor)
                .addPathPatterns("/**");
    }
}

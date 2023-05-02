package com.ssafy.triends.global.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.ssafy.triends.**.mapper"})
public class WebMvcConfiguration {
}

package com.xiechanglei.code.base.web;

import com.xiechanglei.code.base.web.resolver.DateTypeResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 常规配置
 */
@Configuration
public class XieBaseWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept,Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "auth-token")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //添加时间格式化为long类型
        registry.addFormatter(new DateFormatter("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new DateTypeResolver());
    }
}

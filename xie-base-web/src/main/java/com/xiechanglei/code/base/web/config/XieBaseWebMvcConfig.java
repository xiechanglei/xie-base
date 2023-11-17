package com.xiechanglei.code.base.web.config;

import com.xiechanglei.code.base.web.resolver.DateTypeResolver;
import com.xiechanglei.code.base.web.resolver.PageTypeResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 常规配置
 */
@Configuration
@PropertySource("classpath:xie.base.web.properties")
@RequiredArgsConstructor
public class XieBaseWebMvcConfig implements WebMvcConfigurer {
    private final DateTypeResolver dateTypeResolver;
    private final PageTypeResolver pageTypeResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept,Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "auth-token")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(dateTypeResolver);
        resolvers.add(pageTypeResolver);
    }
}

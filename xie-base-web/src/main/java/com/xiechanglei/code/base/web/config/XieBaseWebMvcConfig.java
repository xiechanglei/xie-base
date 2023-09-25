package com.xiechanglei.code.base.web.config;

import com.xiechanglei.code.base.web.config.properties.BaseWebConfigProperties;
import com.xiechanglei.code.base.web.resolver.PageTypeResolver;
import com.xiechanglei.code.base.web.resolver.Pattern2DateResolver;
import com.xiechanglei.code.base.web.resolver.TImeStamp2DateResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.xiechanglei.code.base.web.config.properties.BaseWebConfigProperties.READ_DATE_TYPE_PATTERN;
import static com.xiechanglei.code.base.web.config.properties.BaseWebConfigProperties.READ_DATE_TYPE_TIMESTAMP;

/**
 * 常规配置
 */
@Configuration
@PropertySource("classpath:xie.base.web.properties")
@RequiredArgsConstructor
public class XieBaseWebMvcConfig implements WebMvcConfigurer {
    private final Pattern2DateResolver pattern2DateResolver;
    private final PageTypeResolver pageTypeResolver;
    private final TImeStamp2DateResolver timeStamp2DateResolver;
    private final BaseWebConfigProperties baseWebConfigProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Content-Type", "X-Requested-With", "accept,Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "auth-token")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        if (READ_DATE_TYPE_PATTERN.equals(baseWebConfigProperties.getReadDateType())) {
            resolvers.add(pattern2DateResolver);
        } else if (READ_DATE_TYPE_TIMESTAMP.equals(baseWebConfigProperties.getReadDateType())){
            resolvers.add(timeStamp2DateResolver);
        }
        resolvers.add(pageTypeResolver);
    }
}

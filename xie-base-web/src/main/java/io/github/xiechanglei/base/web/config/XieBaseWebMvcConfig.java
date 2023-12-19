package io.github.xiechanglei.base.web.config;

import io.github.xiechanglei.base.web.properties.XieBaseWebConfigProperties;
import io.github.xiechanglei.base.web.resolver.DateTypeResolver;
import io.github.xiechanglei.base.web.resolver.PageTypeResolver;
import io.github.xiechanglei.base.common.base.array.ArrayHelper;
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
    private final XieBaseWebConfigProperties xieBaseWebConfigProperties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowHeaders = new String[]{"Content-Type", "X-Requested-With", "accept,Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers", "auth-token"};
        if (xieBaseWebConfigProperties.getCrossHeaders() != null && xieBaseWebConfigProperties.getCrossHeaders().length > 0) {
            allowHeaders = ArrayHelper.concat(allowHeaders, xieBaseWebConfigProperties.getCrossHeaders());
        }
        if (xieBaseWebConfigProperties.isCors()) {
            registry.addMapping("/**")
                    .allowedHeaders(allowHeaders)
                    .allowedMethods(xieBaseWebConfigProperties.getCrossMethods())
                    .allowedOrigins(xieBaseWebConfigProperties.getCrossOrigins());
        }
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        if (xieBaseWebConfigProperties.isUseDateResolver()) {
            resolvers.add(dateTypeResolver);
        }
        if (xieBaseWebConfigProperties.isUsePageResolver()) {
            resolvers.add(pageTypeResolver);
        }
    }
}

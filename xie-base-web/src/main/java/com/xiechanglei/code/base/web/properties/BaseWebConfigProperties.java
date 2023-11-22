package com.xiechanglei.code.base.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义配置项目
 */
@Data
@Component
@ConfigurationProperties(prefix = "com.xiechanglei.code.base.web")
public class BaseWebConfigProperties {
    private int size = 20; //分页默认大小
    private int page = 1; //分页默认页码
    private boolean responseAdvice = true; //是否开启统一响应处理
    private boolean exceptionAdvice = true; //是否开启统一异常处理
}
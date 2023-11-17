package com.xiechanglei.code.base.web.config.properties;

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
    public static final String READ_DATE_TYPE_PATTERN = "pattern"; // 使用字符串类型解析时间类型参数
    public static final String READ_DATE_TYPE_TIMESTAMP = "timestamp"; // 使用long类型解析时间类型参数

    private int size = 20; //分页默认大小
    private int page = 1; //分页默认页码
    private String readDateType = READ_DATE_TYPE_PATTERN; // pattern, timestamp  时间类型参数的格式

}

package com.xiechanglei.code.base.web.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "com.xiechanglei.code.base.web")
public class BaseWebConfigProperties {
    private int size = 20;
    private int page = 1;
    private String readDateType = "pattern"; // pattern, timestamp
    public static  final String READ_DATE_TYPE_PATTERN = "pattern";
    public static  final String READ_DATE_TYPE_TIMESTAMP = "timestamp";
}

package com.xiechanglei.code.base.web.config.properties;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "com.xiechanglei.code.base.web")
@Log4j2
public class BaseWebConfigProperties {
    private int size = 20;
    private int page = 1;
}

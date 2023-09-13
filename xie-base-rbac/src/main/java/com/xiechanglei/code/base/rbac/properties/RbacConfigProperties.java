package com.xiechanglei.code.base.rbac.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "com.xiechanglei.code.base.rbac")
public class RbacConfigProperties {
    public static final String LEVEL_MENU = "menu";
    public static final String LEVEL_ACTION = "action";
    private String level;// 权限细分级别，menu,action，默认为action级别
}

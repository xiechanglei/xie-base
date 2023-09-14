package com.xiechanglei.code.base.rbac.properties;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@ConfigurationProperties(prefix = "com.xiechanglei.code.base.rbac")
@Log4j2
public class RbacConfigProperties {
    public static final String LEVEL_MENU = "menu";
    public static final String LEVEL_ACTION = "action";
    private String level = LEVEL_ACTION;// 权限细分级别，menu,action，默认为action级别
    private String path = "/**";//拦截的路径，默认为/**
    private String tokenname = "auth-token";//从header，param，cookie中存放token的key， 默认为auth-token
    private boolean enable = false;//是否禁用rbac权限模块


    @PostConstruct
    public void init() {
        if (isEnable()) {
            log.info("rbac auth: 权限模块加载成功");
            log.info("rbac auth: 权限细分级别为:{}", getLevel());
            log.info("rbac auth: 拦截的路径为:{}", getPath());
            log.info("rbac auth: 存放token的key为:{}", getTokenname());
        }
    }
}

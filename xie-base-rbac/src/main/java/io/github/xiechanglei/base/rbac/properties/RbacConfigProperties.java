package io.github.xiechanglei.base.rbac.properties;

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

    private boolean enable = true;//是否禁用rbac权限模块

    private String level = LEVEL_ACTION;// 权限细分级别，menu,action，默认为action级别

    private String tokenname = "auth-token";//从header，param，cookie中存放token的key， 默认为auth-token

    private boolean auto = true;//是否自动维护权限数据

    private boolean useController = false;//是否使用controller层的注解


    @PostConstruct
    public void init() {
        if (isEnable()) {
            log.info("rbac auth: 权限模块加载成功");
            log.info("rbac auth: 权限细分级别为:{}", getLevel());
            log.info("rbac auth: 存放token的key为:{}", getTokenname());
            if (isAuto()) {
                log.warn("rbac auth: 是否自动维护权限数据:{}，项目复杂之后，此配置会拖慢启动速度，如果权限菜单不再变化，建议设置为false", isAuto());
            } else {
                log.info("rbac auth: 是否自动维护权限数据:{}", isAuto());
            }

        }
    }
}

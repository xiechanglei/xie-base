package com.xiechanglei.code.base.rbac;

import com.xiechanglei.code.base.rbac.init.DataInitiation;
import com.xiechanglei.code.base.rbac.init.TableInitiation;
import com.xiechanglei.code.base.rbac.properties.RbacConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;

/**
 * 数据初始化配置
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "enable", havingValue = "true", matchIfMissing = true)
public class RbacInitConfiguration implements ApplicationContextAware {
    private final RbacConfigProperties rbacConfigProperties;
    private final TableInitiation tableInitiation;
    private final DataInitiation dataInitiation;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        if (rbacConfigProperties.isAuto()) {
            tableInitiation.createTableIfNotExist();
            dataInitiation.initData(applicationContext);
        }

    }
}

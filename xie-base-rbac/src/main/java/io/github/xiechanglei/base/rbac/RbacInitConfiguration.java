package io.github.xiechanglei.base.rbac;

import io.github.xiechanglei.base.rbac.init.DataInitiation;
import io.github.xiechanglei.base.rbac.init.TableInitiation;
import io.github.xiechanglei.base.rbac.properties.RbacConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNull;

/**
 * 数据初始化配置
 */
@EntityScan
@EnableJpaRepositories
@EnableJpaAuditing
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

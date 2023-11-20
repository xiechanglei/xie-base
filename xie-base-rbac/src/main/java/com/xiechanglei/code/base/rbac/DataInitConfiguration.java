package com.xiechanglei.code.base.rbac;

import com.xiechanglei.code.base.rbac.annotation.PermissionAction;
import com.xiechanglei.code.base.rbac.annotation.PermissionMenu;
import com.xiechanglei.code.base.rbac.entity.RbacAuthAction;
import com.xiechanglei.code.base.rbac.entity.RbacAuthMenu;
import com.xiechanglei.code.base.rbac.properties.RbacConfigProperties;
import com.xiechanglei.code.base.rbac.repo.RbacAuthActionRepository;
import com.xiechanglei.code.base.rbac.repo.RbacAuthMenuRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 数据初始化配置
 */
@EntityScan
@EnableJpaRepositories
@EnableJpaAuditing
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "enable", havingValue = "true", matchIfMissing = true)
public class DataInitConfiguration implements ApplicationContextAware {
    private final RbacConfigProperties rbacConfigProperties;
    private final RbacAuthMenuRepository rbacAuthMenuRepository;
    private final RbacAuthActionRepository rbacAuthActionRepository;

    /**
     * 自动初始化权限菜单和权限动作相关的数据
     */
    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        //自动创建表
        if (rbacConfigProperties.isAuto()) {
            initData(applicationContext);
        }
    }

    /**
     * 自动更新权限数据
     */
    public void initData(ApplicationContext applicationContext) {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PermissionMenu.class);
        beansWithAnnotation.values().forEach(bean -> {
            PermissionMenu menuAnnotation = bean.getClass().getAnnotation(PermissionMenu.class);
            if (!rbacAuthMenuRepository.existsById(menuAnnotation.code())) {
                rbacAuthMenuRepository.save(new RbacAuthMenu(menuAnnotation.code(), menuAnnotation.title(), RbacAuthMenu.MENU_TYPE_PAGE));
            }
            Field[] declaredFields = bean.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(PermissionAction.class)) {
                    PermissionAction actionAnnotation = declaredField.getAnnotation(PermissionAction.class);
                    try {
                        String actionCode = declaredField.get(bean).toString();
                        if (!rbacAuthActionRepository.existsById(actionCode)) {
                            rbacAuthActionRepository.save(new RbacAuthAction(actionCode, actionAnnotation.value(), menuAnnotation.code()));
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
    }
}

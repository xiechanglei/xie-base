package com.xiechanglei.code.base.rbac.init;

import com.xiechanglei.code.base.rbac.annotation.PermissionAction;
import com.xiechanglei.code.base.rbac.annotation.PermissionMenu;
import com.xiechanglei.code.base.rbac.entity.RbacAuthAction;
import com.xiechanglei.code.base.rbac.entity.RbacAuthMenu;
import com.xiechanglei.code.base.rbac.properties.RbacConfigProperties;
import com.xiechanglei.code.base.rbac.repo.RbacAuthActionRepository;
import com.xiechanglei.code.base.rbac.repo.RbacAuthMenuRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.spi.PersistenceProvider;
import jakarta.persistence.spi.PersistenceProviderResolverHolder;
import jakarta.persistence.spi.PersistenceUnitInfo;
import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 初始化数据库表
 */

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "enable", havingValue = "true", matchIfMissing = true)
public class TableInitiation {
    private final RbacConfigProperties rbacConfigProperties;
    private final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;
    private final RbacAuthMenuRepository rbacAuthMenuRepository;
    private final RbacAuthActionRepository rbacAuthActionRepository;
    private final List<String> managedClassNamesCache = new ArrayList<>();

    public void init(ApplicationContext applicationContext) throws BeansException {
        if (rbacConfigProperties.isAuto()) {
            createTableIfNotExist();
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

    /**
     * 自动更新rbac的几个表
     */
    public void createTableIfNotExist() {
        PersistenceProvider persistenceProvider = getPersistenceProvider();
        PersistenceUnitInfo persistenceUnitInfo = getPersistenceUnitInfo();
        try {
            EntityManagerFactory managerFactory = persistenceProvider.createContainerEntityManagerFactory(persistenceUnitInfo, getJpaProperties());
            managerFactory.close();
        } finally {
            rollBackManagedClassNames(persistenceUnitInfo);
        }
    }


    /**
     * 获取PersistenceProvider
     */
    private PersistenceProvider getPersistenceProvider() {
        return PersistenceProviderResolverHolder
                .getPersistenceProviderResolver()
                .getPersistenceProviders()
                .stream()
                .filter(p -> p instanceof HibernatePersistenceProvider)
                .findFirst().orElse(null);
    }

    /**
     * 获取PersistenceUnitInfo,并且将managedClassNames中的表数据只留下rbac相关的
     */
    private PersistenceUnitInfo getPersistenceUnitInfo() {
        PersistenceUnitInfo persistenceUnitInfo = entityManagerFactoryBean.getPersistenceUnitInfo();
        assert persistenceUnitInfo != null;
        List<String> managedClassNames = persistenceUnitInfo.getManagedClassNames();
        managedClassNamesCache.clear();
        managedClassNamesCache.addAll(managedClassNames);
        managedClassNamesCache.stream().
                filter(s -> !s.startsWith("com.xiechanglei.code.base.rbac.entity."))
                .forEach(managedClassNames::remove);
        return persistenceUnitInfo;
    }

    /**
     * 获取jpa的配置参数,修改hibernate.hbm2ddl.auto为update,其他参数不变
     */
    public Map<String, Object> getJpaProperties() {
        Map<String, Object> jpaPropertyMap = entityManagerFactoryBean.getJpaPropertyMap();
        Map<String, Object> newProperties = new HashMap<>(jpaPropertyMap);
        newProperties.put("hibernate.hbm2ddl.auto", "update");
//        newProperties.put("hibernate.show_sql", "true");
        return newProperties;
    }

    /**
     * 回滚managedClassNames
     */
    private void rollBackManagedClassNames(PersistenceUnitInfo persistenceUnitInfo) {
        List<String> managedClassNames = persistenceUnitInfo.getManagedClassNames();
        managedClassNames.clear();
        managedClassNames.addAll(managedClassNamesCache);
    }
}

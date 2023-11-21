package com.xiechanglei.code.base.rbac.init;

import com.xiechanglei.code.base.rbac.annotation.PermissionAction;
import com.xiechanglei.code.base.rbac.annotation.PermissionMenu;
import com.xiechanglei.code.base.rbac.entity.RbacAuthAction;
import com.xiechanglei.code.base.rbac.entity.RbacAuthMenu;
import com.xiechanglei.code.base.rbac.repo.RbacAuthActionRepository;
import com.xiechanglei.code.base.rbac.repo.RbacAuthMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 在项目启动的时候，自动更新权限数据,更新的规则是:
 * 1.寻找所有的带有@PermissionMenu注解的类，将其注解的code和title保存到数据库中
 */
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "enable", havingValue = "true", matchIfMissing = true)
public class DataInitiation {
    private final RbacAuthMenuRepository rbacAuthMenuRepository;
    private final RbacAuthActionRepository rbacAuthActionRepository;
    /**
     * 自动更新权限数据,
     * TODO :
     * 1. menu 跟action的名字无法更新
     * 2. 废弃的权限无法删除
     * 3. 效率太低 优先级别不是很高,可以通过其他的方案解决
     * 注意的问题:
     * 1.不能丢失菜单权限中的可以被维护的数据
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

package com.xiechanglei.code.base.rbac.init;

import com.xiechanglei.code.base.rbac.annotation.PermissionAction;
import com.xiechanglei.code.base.rbac.annotation.PermissionMenu;
import com.xiechanglei.code.base.rbac.entity.RbacAuthAction;
import com.xiechanglei.code.base.rbac.entity.RbacAuthMenu;
import com.xiechanglei.code.base.rbac.repo.RbacAuthActionRepository;
import com.xiechanglei.code.base.rbac.repo.RbacAuthMenuRepository;
import com.xiechanglei.code.base.rbac.repo.RbacAuthRoleRefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
    private final RbacAuthRoleRefRepository rbacAuthRoleRefRepository;

    /**
     * 自动更新权限数据
     */
    public void initData(ApplicationContext applicationContext) {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(PermissionMenu.class);
        List<RbacAuthMenu> newMenus = new ArrayList<>();
        List<RbacAuthAction> newActions = new ArrayList<>();
        beansWithAnnotation.values().forEach(bean -> {
            PermissionMenu menuAnnotation = bean.getClass().getAnnotation(PermissionMenu.class);
            newMenus.add(new RbacAuthMenu(menuAnnotation.code(), menuAnnotation.title(), RbacAuthMenu.MENU_TYPE_PAGE));
            Field[] declaredFields = bean.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(PermissionAction.class)) {
                    PermissionAction actionAnnotation = declaredField.getAnnotation(PermissionAction.class);
                    try {
                        String actionCode = declaredField.get(bean).toString();
                        newActions.add(new RbacAuthAction(actionAnnotation.value(), actionCode, menuAnnotation.code()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });
        updateMenu(newMenus);
        updateAction(newActions);
    }

    /**
     * 更新菜单
     */
    public void updateMenu(List<RbacAuthMenu> newMenus) {
        StoreHandler<RbacAuthMenu> menuStoreHandler = new StoreHandler<>(rbacAuthMenuRepository.findAll(), RbacAuthMenu::getId, (m1, m2) -> {
            if (!m1.getMenuName().equals(m2.getMenuName())) {
                m1.setMenuName(m2.getMenuName());
                return true;
            }
            return false;
        });
        newMenus.forEach(menuStoreHandler::add);
        menuStoreHandler.getNeedDelete().forEach(menu -> {
            rbacAuthRoleRefRepository.deleteByMenuId(menu.getId());
            rbacAuthMenuRepository.delete(menu);
        });
        rbacAuthMenuRepository.saveAll(menuStoreHandler.getNeedUpdate().values());
    }

    /**
     * 更新action
     */
    public void updateAction(List<RbacAuthAction> newActions) {
        StoreHandler<RbacAuthAction> actionStoreHandler = new StoreHandler<>(rbacAuthActionRepository.findAll(), RbacAuthAction::getActionCode, (a1, a2) -> {
            if (!a1.getActionName().equals(a2.getActionName())) {
                a1.setActionName(a2.getActionName());
                return true;
            }
            return false;
        });
        newActions.forEach(actionStoreHandler::add);
        actionStoreHandler.getNeedDelete().forEach(action -> {
            rbacAuthRoleRefRepository.deleteByActionId(action.getActionCode());
            rbacAuthActionRepository.delete(action);
        });
        rbacAuthActionRepository.saveAll(actionStoreHandler.getNeedUpdate().values());
    }
}

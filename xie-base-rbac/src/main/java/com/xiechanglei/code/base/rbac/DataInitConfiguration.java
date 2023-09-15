package com.xiechanglei.code.base.rbac;

import com.xiechanglei.code.base.rbac.annotation.AuthCodeManager;
import com.xiechanglei.code.base.rbac.entity.RbacAuthAction;
import com.xiechanglei.code.base.rbac.entity.RbacAuthMenu;
import com.xiechanglei.code.base.rbac.properties.RbacConfigProperties;
import com.xiechanglei.code.base.rbac.repo.RbacAuthActionRepository;
import com.xiechanglei.code.base.rbac.repo.RbacAuthMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * 数据初始化配置
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "enable", havingValue = "true")
public class DataInitConfiguration implements ApplicationContextAware {
    private final RbacConfigProperties rbacConfigProperties;
    private final RbacAuthMenuRepository rbacAuthMenuRepository;
    private final RbacAuthActionRepository rbacAuthActionRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (rbacConfigProperties.isAuto()) {
            AuthCodeManager.authActionMap.values().forEach(action -> {
                if (!rbacAuthActionRepository.existsById(action.getActionCode())) {
                    rbacAuthActionRepository.save(new RbacAuthAction(action.getActionName(), action.getActionCode(), action.getAuthMenu().getMenuId()));
                }
            });
            AuthCodeManager.authMenuMap.values().forEach(menu -> {
                if (!rbacAuthMenuRepository.existsById(menu.getMenuId())) {
                    rbacAuthMenuRepository.save(new RbacAuthMenu(menu.getMenuId(), menu.getMenuName(), RbacAuthMenu.MENU_TYPE_PAGE));
                }
            });
        }
    }
}

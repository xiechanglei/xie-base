package com.xiechanglei.code.base.rbac;

import com.xiechanglei.code.base.rbac.annotation.AuthCodeManager;
import com.xiechanglei.code.base.rbac.entity.RbacAuthAction;
import com.xiechanglei.code.base.rbac.entity.RbacAuthMenu;
import com.xiechanglei.code.base.rbac.properties.RbacConfigProperties;
import com.xiechanglei.code.base.rbac.repo.RbacAuthActionRepository;
import com.xiechanglei.code.base.rbac.repo.RbacAuthMenuRepository;
import jakarta.annotation.Nonnull;
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

    /**
     * 自动初始化权限菜单和权限动作相关的数据
     */
    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        //获取所有的controller
        if (rbacConfigProperties.isAuto()) {
            AuthCodeManager.authActionMap.values().forEach(action -> {
                if (!rbacAuthActionRepository.existsById(action.actionCode())) {
                    rbacAuthActionRepository.save(new RbacAuthAction(action.actionName(), action.actionCode(), action.authMenu().menuId()));
                }
            });
            AuthCodeManager.authMenuMap.values().forEach(menu -> {
                if (!rbacAuthMenuRepository.existsById(menu.menuId())) {
                    rbacAuthMenuRepository.save(new RbacAuthMenu(menu.menuId(), menu.menuName(), RbacAuthMenu.MENU_TYPE_PAGE));
                }
            });
        }
    }
}

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
 * 数据初始化配置,配置一些常用的菜单与功能
 * 1.角色管理 （角色列表，添加角色，禁用角色，编辑角色，编辑角色权限）
 * 2.菜单管理 （菜单列表，添加菜单，添加操作，编辑菜单，禁用菜单）
 * 3.用户管理 （用户列表，编辑用户，用户授权，禁用用户） TODO 用户禁用影响到鉴定权限
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
        //TODO 暂时不能读取到这些菜单数据
        if (rbacConfigProperties.isAuto()) {
            AuthCodeManager.authActionMap.values().forEach(action -> {
                if (!rbacAuthActionRepository.existsById(action.getActionCode())) {
                    rbacAuthActionRepository.save(new RbacAuthAction(action.getActionCode(), action.getActionName(), action.getAuthMenu().getMenuId()));
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

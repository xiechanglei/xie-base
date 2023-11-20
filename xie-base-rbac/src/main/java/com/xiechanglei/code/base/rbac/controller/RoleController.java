package com.xiechanglei.code.base.rbac.controller;

import com.xiechanglei.code.base.rbac.annotation.RbacAuth;
import com.xiechanglei.code.base.rbac.entity.RbacAuthRole;
import com.xiechanglei.code.base.rbac.internal.RolePermission;
import com.xiechanglei.code.base.rbac.repo.RbacAuthRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色相关的接口
 */
@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "use-controller", havingValue = "true")
public class RoleController {
    private final RbacAuthRoleRepository rbacAuthRoleRepository;

    /**
     * 获取所有的角色
     */
    @RbacAuth(RolePermission.ADD)
    @RequestMapping("/rbac/role/list")
    public List<RbacAuthRole> listAllRole() {
        return rbacAuthRoleRepository.findAll();
    }

    //添加角色

    //修改角色

    //禁用角色
}

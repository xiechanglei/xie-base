package com.xiechanglei.code.base.rbac.controller;

import com.xiechanglei.code.base.rbac.annotation.RbacAuth;
import com.xiechanglei.code.base.rbac.entity.RbacAuthRole;
import com.xiechanglei.code.base.rbac.internal.InternalAuthCode;
import com.xiechanglei.code.base.rbac.repo.RbacAuthRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色相关的接口
 *
 */
@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RbacAuthRoleRepository rbacAuthRoleRepository;

    /**
     * 获取所有的角色
     */
    @RbacAuth(InternalAuthCode.ROLE.QUERY)
    @RequestMapping("/rbac/role/list")
    public List<RbacAuthRole> listAllRole() {
        return rbacAuthRoleRepository.findAll();
    }

    //添加角色

    //修改角色

    //禁用角色

    //TODO 获取角色的权限列表

    //TODO 修改角色的权限列表
}

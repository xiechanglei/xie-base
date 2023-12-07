package com.xiechanglei.code.base.rbac.controller;

import com.xiechanglei.code.base.rbac.annotation.RbacAuth;
import com.xiechanglei.code.base.rbac.entity.RbacAuthRole;
import com.xiechanglei.code.base.rbac.internal.ErrorCode;
import com.xiechanglei.code.base.rbac.internal.RolePermission;
import com.xiechanglei.code.base.rbac.repo.RbacAuthRoleRepository;
import com.xiechanglei.code.base.rbac.repo.RbacAuthUserRoleRepository;
import com.xiechanglei.code.base.common.bean.message.MessageException;
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
    private final RbacAuthUserRoleRepository rbacAuthUserRoleRepository;

    /**
     * 获取所有的角色
     */
    @RbacAuth(RolePermission.QUERY)
    @RequestMapping("/rbac/role/list")
    public List<RbacAuthRole> listAllRole() {
        return rbacAuthRoleRepository.findAll();
    }

    /**
     * 添加角色,角色名称唯一
     */
    @RbacAuth(RolePermission.ADD)
    @RequestMapping("/rbac/role/add")
    public void addRole(String roleName) {
        if (rbacAuthRoleRepository.existsByRoleName(roleName)) {
            throw MessageException.of("角色名称已存在", ErrorCode.ROLE_EXISTS);
        }
        rbacAuthRoleRepository.save(RbacAuthRole.create(roleName));
    }

    /**
     * 删除角色，角色下有用户时不能删除
     */
    @RbacAuth(RolePermission.DELETE)
    @RequestMapping("/rbac/role/delete")
    public void deleteRole(String roleId) {
        if (rbacAuthUserRoleRepository.existsByRoleId(roleId)) {
            throw MessageException.of("角色下有用户，不能删除", ErrorCode.ROLE_CAN_NOT_DELETE);
        }
        rbacAuthRoleRepository.deleteById(roleId);
    }

    /**
     * 修改角色名称
     */
    @RbacAuth(RolePermission.UPDATE)
    @RequestMapping("/rbac/role/update")
    public void updateRole(String roleId, String roleName) {
        RbacAuthRole byRoleName = rbacAuthRoleRepository.findByRoleName(roleName);
        if (byRoleName != null && !byRoleName.getId().equals(roleId)) {
            throw MessageException.of("角色名称已存在", ErrorCode.ROLE_EXISTS);
        }
        rbacAuthRoleRepository.updateRoleNameById(roleName, roleId);
    }

    /**
     * 启用/禁用角色
     */
    @RbacAuth(RolePermission.ENABLE)
    @RequestMapping("/rbac/role/enable")
    public void enableRole(String roleId, boolean enable) {
        rbacAuthRoleRepository.updateEnableById(enable, roleId);
    }

}

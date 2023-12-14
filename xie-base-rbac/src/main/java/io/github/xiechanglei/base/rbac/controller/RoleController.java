package io.github.xiechanglei.base.rbac.controller;

import io.github.xiechanglei.base.common.bean.message.MessageException;
import io.github.xiechanglei.base.rbac.annotation.RbacAuth;
import io.github.xiechanglei.base.rbac.entity.RbacAuthRole;
import io.github.xiechanglei.base.rbac.internal.ErrorConstant;
import io.github.xiechanglei.base.rbac.internal.RolePermission;
import io.github.xiechanglei.base.rbac.repo.RbacAuthRoleRepository;
import io.github.xiechanglei.base.rbac.repo.RbacAuthUserRoleRepository;
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
@ConditionalOnProperty(prefix = "io.github.xiechanglei.base.rbac", name = "use-controller", havingValue = "true")
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
            throw MessageException.of(ErrorConstant.ROLE_EXISTS);
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
            throw MessageException.of(ErrorConstant.ROLE_CAN_NOT_DELETE);
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
            throw MessageException.of(ErrorConstant.ROLE_EXISTS);
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

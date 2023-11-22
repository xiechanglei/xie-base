package com.xiechanglei.code.base.rbac.repo;

import com.xiechanglei.code.base.rbac.entity.RbacAuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * RBAC角色表查询接口
 */
public interface RbacAuthRoleRepository extends JpaRepository<RbacAuthRole, String>, JpaSpecificationExecutor<RbacAuthRole> {
    /**
     * 根据角色名称判断是否存在
     *
     * @param roleName 角色名称
     * @return 是否存在
     */
    boolean existsByRoleName(String roleName);

    /**
     * 根据角色名称获取角色
     *
     * @param roleName 角色名称
     * @return 角色
     */
    RbacAuthRole findByRoleName(String roleName);

    /**
     * 根据角色id修改角色名称
     *
     * @param roleName 角色名称
     * @param roleId   角色id
     */
    @Modifying
    @Transactional
    @Query("update RbacAuthRole set roleName = ?1 where id = ?2")
    void updateRoleNameById(String roleName, String roleId);

    /**
     * 根据角色id修改角色是否启用
     *
     * @param enable 是否启用
     * @param roleId 角色id
     */
    @Modifying
    @Transactional
    @Query("update RbacAuthRole set enable = ?1 where id = ?2")
    void updateEnableById(Boolean enable, String roleId);
}

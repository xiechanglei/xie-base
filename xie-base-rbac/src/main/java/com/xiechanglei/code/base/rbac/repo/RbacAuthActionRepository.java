package com.xiechanglei.code.base.rbac.repo;

import com.xiechanglei.code.base.rbac.entity.RbacAuthAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RbacAuthActionRepository extends JpaRepository<RbacAuthAction, String>,JpaSpecificationExecutor<RbacAuthAction> {
    @Query("select ac.actionCode from RbacAuthAction ac ,RbacAuthRoleRef ref ,RbacAuthMenu menu,RbacAuthUserRole ur,RbacAuthRole  r " +
            "where ref.authType='menu' " +
            "and ref.menuId = menu.id " +
            "and menu.id = ac.menuId and menu.enable = true " +
            "and ref.roleId = ur.roleId  and ur.roleId = r.id and r.enable = true " +
            "and ur.userId = ?1 " +
            "and ac.actionCode in ?2 ")
    List<String> findActionCodeByRoleMenuAction(String userId, List<String> actionCodes);

    @Query("select ac.actionCode from RbacAuthAction ac ,RbacAuthRoleRef ref ,RbacAuthMenu menu,RbacAuthUserRole ur,RbacAuthRole  r " +
            "where ref.authType='action' " +
            "and ref.actionId = ac.actionCode " +
            "and ac.menuId = menu.id and menu.enable = true " +
            "and ref.roleId = ur.roleId and ur.roleId = r.id and r.enable = true " +
            "and ur.userId = ?1 " +
            "and ac.actionCode in ?2")
    List<String> findActionCodeByRoleAction(String userId, List<String> actionCodes);
}

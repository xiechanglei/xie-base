package com.xiechanglei.code.base.rbac.repo;

import com.xiechanglei.code.base.rbac.entity.RbacAuthAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RbacAuthActionRepository extends JpaRepository<RbacAuthAction, String> {
    @Query("select ac.actionCode from RbacAuthAction ac ,RbacAuthRoleRef ref ,RbacAuthMenu menu,RbacAuthUserRole ur " +
            "where ref.authType=0 " +
            "and ref.menuId = menu.id " +
            "and menu.id = ac.menuId " +
            "and ref.roleId = ur.roleId " +
            "and ur.userId = ?1 " +
            "and ac.actionCode in ?2 ")
    List<String> findActionCodeByRoleMenuAction(String userId, List<String> actionCodes);

    @Query("select ac.actionCode from RbacAuthAction ac ,RbacAuthRoleRef ref ,RbacAuthUserRole ur " +
            "where ref.authType=1 " +
            "and ref.actionId = ac.id " +
            "and ref.roleId = ur.roleId " +
            "and ur.userId = ?1 " +
            "and ac.actionCode in ?2")
    List<String> findActionCodeByRoleAction(String userId, List<String> actionCodes);
}

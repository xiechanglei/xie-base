package io.github.xiechanglei.base.rbac.repo;

import io.github.xiechanglei.base.rbac.entity.RbacAuthRoleRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RbacAuthRoleRefRepository extends JpaRepository<RbacAuthRoleRef, String>, JpaSpecificationExecutor<RbacAuthRoleRef> {
    void deleteByMenuId(String menuId);

    void deleteByActionId(String actionId);
}

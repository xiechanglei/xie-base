package com.xiechanglei.code.base.rbac.repo;

import com.xiechanglei.code.base.rbac.entity.RbacAuthUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RbacAuthUserRoleRepository extends JpaRepository<RbacAuthUserRole, String>, JpaSpecificationExecutor<RbacAuthUserRole> {
    boolean existsByRoleId(String roleId);
}

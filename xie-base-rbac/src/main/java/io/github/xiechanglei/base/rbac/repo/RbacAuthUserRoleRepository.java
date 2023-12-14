package io.github.xiechanglei.base.rbac.repo;

import io.github.xiechanglei.base.rbac.entity.RbacAuthUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RbacAuthUserRoleRepository extends JpaRepository<RbacAuthUserRole, String>, JpaSpecificationExecutor<RbacAuthUserRole> {
    boolean existsByRoleId(String roleId);
}

package com.xiechanglei.code.base.rbac.repo;

import com.xiechanglei.code.base.rbac.entity.RbacAuthRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RbacAuthRoleRepository extends JpaRepository<RbacAuthRole, String>, JpaSpecificationExecutor<RbacAuthRole> {
}

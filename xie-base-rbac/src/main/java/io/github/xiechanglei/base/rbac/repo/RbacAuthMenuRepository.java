package io.github.xiechanglei.base.rbac.repo;

import io.github.xiechanglei.base.rbac.entity.RbacAuthMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RbacAuthMenuRepository extends JpaRepository<RbacAuthMenu, String>, JpaSpecificationExecutor<RbacAuthMenu> {
}

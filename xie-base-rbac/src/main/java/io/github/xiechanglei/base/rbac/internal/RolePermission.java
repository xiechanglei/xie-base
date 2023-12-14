package io.github.xiechanglei.base.rbac.internal;

import io.github.xiechanglei.base.rbac.annotation.PermissionAction;
import io.github.xiechanglei.base.rbac.annotation.PermissionMenu;

@PermissionMenu(code = "rbac:menu:role:manager", title = "角色管理")
public class RolePermission {
    @PermissionAction("角色新增")
    public static final String ADD = "rbac:action:role:add";

    @PermissionAction("角色查询")
    public static final String QUERY = "rbac:action:role:query";

    @PermissionAction("角色删除")
    public static final String DELETE = "rbac:action:role:delete";

    @PermissionAction("角色修改")
    public static final String UPDATE = "rbac:action:role:update";

    @PermissionAction("角色禁用启用")
    public static final String ENABLE = "rbac:action:role:enable";

}

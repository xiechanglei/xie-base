package com.xiechanglei.code.base.rbac.internal;

import com.xiechanglei.code.base.rbac.annotation.PermissionAction;
import com.xiechanglei.code.base.rbac.annotation.PermissionMenu;

@PermissionMenu(code = "rbac:menu:role:manager", title = "角色管理")
public class MenuPermission {
    @PermissionAction("角色新增")
    public static final String ADD = "rbac:action:role:add";

}

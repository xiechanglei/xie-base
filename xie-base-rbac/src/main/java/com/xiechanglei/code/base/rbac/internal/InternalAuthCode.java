package com.xiechanglei.code.base.rbac.internal;

import com.xiechanglei.code.base.rbac.annotation.AuthAction;
import com.xiechanglei.code.base.rbac.annotation.AuthCodeManager;
import com.xiechanglei.code.base.rbac.annotation.AuthMenu;
import org.springframework.stereotype.Component;

@Component
public interface InternalAuthCode {
    @Component
    class ROLE {
        public static final String ADD = "rbac:action:role:add";
        public static final String QUERY = "rbac:action:role:query";
        public static final String EDIT = "rbac:action:role:edit";
        public static final String ENABLE = "rbac:action:role:enable";

        static {
            AuthMenu roleMenu = new AuthMenu("rbac:menu:role:manager", "角色管理");
            AuthCodeManager.register(new AuthAction(ADD, "角色新增", roleMenu));
            AuthCodeManager.register(new AuthAction(QUERY, "角色查询", roleMenu));
            AuthCodeManager.register(new AuthAction(EDIT, "角色编辑", roleMenu));
            AuthCodeManager.register(new AuthAction(ENABLE, "角色启用/禁用", roleMenu));
        }
    }

    @Component
    class MENU {
        public static final String QUERY = "rbac:action:menu:query";
        public static final String EDIT = "rbac:action:menu:edit";
        public static final String ENABLE = "rbac:action:menu:enable";

        static {
            AuthMenu menuMenu = new AuthMenu("rbac:menu:menu:manager", "菜单管理");
            AuthCodeManager.register(new AuthAction(QUERY, "菜单查询", menuMenu));
            AuthCodeManager.register(new AuthAction(EDIT, "菜单编辑", menuMenu));
            AuthCodeManager.register(new AuthAction(ENABLE, "菜单启用/禁用", menuMenu));
        }
    }

    @Component
    class USER {
        public static final String QUERY = "rbac:action:user:query";
        public static final String ADD = "rbac:action:user:add";
        public static final String EDIT = "rbac:action:user:edit";
        public static final String ENABLE = "rbac:action:user:enable";
        public static final String CHANGE_ROLE = "rbac:action:user:change:role";

        static {
            AuthMenu userMenu = new AuthMenu("rbac:menu:user:manager", "用户管理");
            AuthCodeManager.register(new AuthAction(QUERY, "用户查询", userMenu));
            AuthCodeManager.register(new AuthAction(ADD, "用户新增", userMenu));
            AuthCodeManager.register(new AuthAction(EDIT, "用户编辑", userMenu));
            AuthCodeManager.register(new AuthAction(ENABLE, "用户启用/禁用", userMenu));
            AuthCodeManager.register(new AuthAction(CHANGE_ROLE, "用户角色变更", userMenu));
        }
    }
}

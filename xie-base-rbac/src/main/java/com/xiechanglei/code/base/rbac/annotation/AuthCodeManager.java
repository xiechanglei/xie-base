package com.xiechanglei.code.base.rbac.annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于管理注册权限操作码的，可以为全局提供自动去维护menu和code到数据库的机制
 */
public class AuthCodeManager {
    public static Map<String, AuthAction> authActionMap = new HashMap<>();
    public static Map<String, AuthMenu> authMenuMap = new HashMap<>();

    public static void register(AuthAction authAction) {
        authActionMap.put(authAction.actionCode(), authAction);
        authMenuMap.put(authAction.authMenu().menuId(), authAction.authMenu());
    }
}

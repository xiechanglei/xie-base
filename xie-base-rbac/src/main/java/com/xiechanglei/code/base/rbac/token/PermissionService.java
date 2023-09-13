package com.xiechanglei.code.base.rbac.token;

import java.util.List;

public interface PermissionService {
    //从当前的请求中获取登陆的用户权限列表
    boolean hasPermission(List<String> permissions);
}

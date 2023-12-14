package io.github.xiechanglei.base.rbac.token;

/**
 * 可以自己重写权限校验逻辑
 */
public interface PermissionService {
    //从当前的请求中获取登陆的用户权限列表
    boolean hasPermission(String[] permissions);
}

package com.xiechanglei.code.base.rbac.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "enable", havingValue = "true")
public class RbacService {
    //TODO 获取所有的角色列表

    //TODO 修改某个用户的角色

    //TODO 获取某个用户的角色列表

    //TODO 获取角色的权限列表

    //TODO 修改角色的权限列表

    //TODO 获取全权限树形结构

    //TODO 获取某个用户的菜单列表

    //默认用户管理实现模块，减少一些不必要的工作量，用户登陆添加，删除，列表，查询，修改密码等

}

package com.xiechanglei.code.base.rbac.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 *  1.角色管理 （角色列表，添加角色，禁用角色，编辑角色，编辑角色权限）
 *  * 2.菜单管理 （菜单列表，添加菜单，添加操作，编辑菜单，禁用菜单）
 *  * 3.用户管理 （用户列表，编辑用户，用户授权，禁用用户） TODO 用户禁用影响到鉴定权限
 */
@Service
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "enable", havingValue = "true")
@RequiredArgsConstructor
public class RbacService {

    //TODO 获取所有角色

    //TODO 修改某个用户的角色

    //TODO 获取某个用户的角色列表

    //TODO 获取角色的权限列表

    //TODO 修改角色的权限列表

    //TODO 获取全权限树形结构

    //TODO 获取某个用户的菜单列表

    //默认用户管理实现模块，减少一些不必要的工作量，用户登陆添加，删除，列表，查询，修改密码等



}

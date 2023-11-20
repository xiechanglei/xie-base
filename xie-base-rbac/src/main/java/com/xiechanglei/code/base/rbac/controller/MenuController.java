package com.xiechanglei.code.base.rbac.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "use-controller", havingValue = "true")
public class MenuController {


    //TODO 获取全权限树形结构

    //TODO 修改菜单基本信息

    //TODO 禁用菜单
}

package com.xiechanglei.code.base.rbac.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnBean(InternalControllerMessageBuilder.class)
@RequiredArgsConstructor
public class MenuController {
    private final InternalControllerMessageBuilder internalControllerMessageBuilder;


    //TODO 获取全权限树形结构

    //TODO 修改菜单基本信息

    //TODO 禁用菜单
}

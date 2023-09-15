package com.xiechanglei.code.base.rbac.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnBean(InternalControllerMessageBuilder.class)
@RequiredArgsConstructor
public class UserController {
    private final InternalControllerMessageBuilder internalControllerMessageBuilder;

    //TODO 修改某个用户的角色

    //TODO 获取某个用户的角色列表

    //TODO 获取某个用户的菜单列表

    //TODO 禁用用户
}

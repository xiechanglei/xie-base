package io.github.xiechanglei.base.rbac.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "com.xiechanglei.code.base.rbac", name = "use-controller", havingValue = "true")
public class UserController {
    //TODO 修改某个用户的角色

    //TODO 获取某个用户的角色列表

    //TODO 获取某个用户的菜单列表

    //TODO 禁用用户
}

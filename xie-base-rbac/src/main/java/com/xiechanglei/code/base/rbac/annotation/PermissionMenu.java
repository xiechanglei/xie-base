package com.xiechanglei.code.base.rbac.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 菜单权限字段类的注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface PermissionMenu {
    String code();// 菜单权限码,不同的菜单之间应该是唯一的
    String title();// 权限名称,用来描述菜单信息
}

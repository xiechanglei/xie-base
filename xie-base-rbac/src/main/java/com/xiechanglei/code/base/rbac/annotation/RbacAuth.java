package com.xiechanglei.code.base.rbac.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 角色与用户是变化的，而菜单与操作是固定的，某个菜单下对应的是多个操作
 * 我们可以将操作组织成最小的权限控制单元，用于拦截对应的controller,
 * 任何一个http接口都需要用户具有一个或者多个菜单下的一个或者多个操作的权限才能访问
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RbacAuth {
    String[] value(); //一个或者多个权限操作码

    String title() default ""; //日志标题，当设置此值的时候，会记录用户的操作日志
}

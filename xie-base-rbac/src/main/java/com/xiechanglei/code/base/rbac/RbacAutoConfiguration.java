package com.xiechanglei.code.base.rbac;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 封装RBAC权限模型
 * 对于大部分的系统来说，用户，角色，菜单，操作之间的权限关系是固定的，
 * 所以我们可以将这些关系抽象成一个RBAC模型，做成spring boot的starter，提供给不同的项目进行使用
 */
@EntityScan
@EnableJpaRepositories
@ComponentScan("com.xiechanglei.code.base.rbac")
@EnableAutoConfiguration(exclude = {RbacAutoConfiguration.class})
public class RbacAutoConfiguration { }

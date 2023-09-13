package com.xiechanglei.code.base.rbac.entity;

import com.xiechanglei.code.base.common.jpa.entity.UUIDIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RBAC角色权限关联表
 */
@Entity
@Getter
@Setter
@Table(name = "rbac_auth_role_ref")
public class RbacAuthRoleRef extends UUIDIdEntity {
    @Column(length = 32, nullable = false, columnDefinition = "varchar(32) comment '角色id'")
    private String roleId;
    @Column(length = 1, nullable = false, columnDefinition = "int(1) comment '权限类型，0 表示菜单权限，1表示操作权限，菜单授权模式下，该菜单下的所有操作都有权限，操作授权模式下，仅仅授权菜单'")
    private Integer authType;//0 表示菜单权限，1表示操作权限，菜单授权模式下，该菜单下的所有操作都有权限，操作授权模式下，仅仅授权菜单
    @Column(length = 32 , columnDefinition = "varchar(32) comment '菜单id'")
    private String menuId;
    @Column(length = 32, columnDefinition = "varchar(32) comment '操作id'")
    private String actionId;
}

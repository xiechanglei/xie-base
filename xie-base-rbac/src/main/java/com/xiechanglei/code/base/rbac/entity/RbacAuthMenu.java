package com.xiechanglei.code.base.rbac.entity;

import com.xiechanglei.code.base.common.jpa.entity.UUIDIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * RBAC权限模型下的菜单
 */
@Getter
@Setter
@Entity
@Table(name = "rbac_auth_menu")
public class RbacAuthMenu extends UUIDIdEntity {
    @Column(length = 100, columnDefinition = "varchar(200) comment '菜单名称'")
    private String menuName;
    @Column(length = 100, columnDefinition = "varchar(200) comment '菜单图标'")
    private String menuIcon;
    @Column(length = 200, columnDefinition = "varchar(200) comment '菜单url'")
    private String menuUrl;
    @Column(length = 1, nullable = false, columnDefinition = "int(1) comment '菜单类型，0表示folder，1表示page'")
    private Integer menuType; //菜单类型，0表示folder，1表示page
    @Column(length = 5, columnDefinition = "int(5) comment '菜单排序号码'")
    private Integer orderNumber; //菜单排序号码
    @Column(length = 32, columnDefinition = "varchar(32) comment '父菜单id'")
    private String parentId; //父菜单id
    @Column(length = 1, columnDefinition = "int(1) comment '是否启用'")
    private  Boolean enable;
}

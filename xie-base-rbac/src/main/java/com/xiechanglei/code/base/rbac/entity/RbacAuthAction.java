package com.xiechanglei.code.base.rbac.entity;

import com.xiechanglei.code.base.common.jpa.entity.UUIDIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * RBAC权限模型下的操作
 */
@Entity
@Getter
@Setter
@Table(name = "rbac_auth_action",indexes = {
        @Index(name = "action_code_unique", unique = true, columnList = "actionCode")
})
public class RbacAuthAction extends UUIDIdEntity {
    @Column(length = 100, nullable = false, columnDefinition = "varchar(200) comment '操作名称'")
    private String actionName;
    @Column(length = 20, nullable = false, columnDefinition = "varchar(200) comment '操作码，唯一，不可修改'")
    private String actionCode;
    @Column(length = 32, nullable = false, columnDefinition = "varchar(32) comment '菜单id'")
    private String menuId;
}

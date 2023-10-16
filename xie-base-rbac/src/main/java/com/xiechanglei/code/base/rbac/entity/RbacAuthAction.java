package com.xiechanglei.code.base.rbac.entity;

import com.xiechanglei.code.base.common.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * RBAC权限模型下的操作
 */
@Entity
@Getter
@Setter
@Table(name = "rbac_auth_action")
@NoArgsConstructor
public class RbacAuthAction implements BaseEntity {
    @Id
    @Column(length = 100, nullable = false, columnDefinition = "varchar(100) comment '物理主键,操作码，唯一，不可修改'")
    private String actionCode;

    @Column(length = 100, nullable = false, columnDefinition = "varchar(200) comment '操作名称'")
    private String actionName;

    @Column(length = 32, nullable = false, columnDefinition = "varchar(32) comment '菜单id'")
    private String menuId;

    public RbacAuthAction(String actionName, String actionCode, String menuId) {
        this.actionName = actionName;
        this.actionCode = actionCode;
        this.menuId = menuId;
    }

}

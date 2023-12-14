package io.github.xiechanglei.base.rbac.entity;

import io.github.xiechanglei.base.common.jpa.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


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

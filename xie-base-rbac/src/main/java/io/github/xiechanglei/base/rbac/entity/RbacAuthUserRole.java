package io.github.xiechanglei.base.rbac.entity;

import io.github.xiechanglei.base.common.jpa.entity.UUIDIdEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * RBAC用户角色对应表,表示一个用户可以拥有多个角色
 */
@Entity
@Getter
@Setter
@Table(name = "rbac_auth_user_role",indexes = {
        @Index(name = "user_id_unique", unique = true, columnList = "userId,roleId"),
        @Index(name = "role_id_unique", columnList = "roleId"),
        @Index(name = "role_id_unique", columnList = "userId")
})
public class RbacAuthUserRole extends UUIDIdEntity  {
    @Column(length = 32, nullable = false, columnDefinition = "varchar(32) comment '用户ID'")
    private String userId;
    @Column(length = 32, nullable = false, columnDefinition = "varchar(32) comment '角色ID'")
    private String roleId;
}

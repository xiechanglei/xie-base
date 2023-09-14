package com.xiechanglei.code.base.rbac.entity;

import com.xiechanglei.code.base.common.jpa.entity.UUIDIdAndTimeFieldEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * RBAC角色表
 */
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rbac_auth_role", indexes = {
        @Index(name = "role_name_unique", unique = true, columnList = "roleName")
})
public class RbacAuthRole extends UUIDIdAndTimeFieldEntity {
    @Column(length = 100, nullable = false, columnDefinition = "varchar(200) comment '角色名称，唯一'")
    private String roleName;//角色名称，唯一

    @Column(length = 1, columnDefinition = "int(1) comment '是否启用'")
    private  Boolean enable;
}

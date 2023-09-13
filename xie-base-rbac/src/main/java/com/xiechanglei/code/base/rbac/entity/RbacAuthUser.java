package com.xiechanglei.code.base.rbac.entity;

import com.xiechanglei.code.base.common.jpa.entity.UUIDIdAndTimeFieldEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * RBAC用户表,如果想要扩充表的业务字段，那么请使用1对1关联表来处理,或者扩展字段之后使用新Entity来管理用户
 */
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rbac_auth_user", indexes = {
        @Index(name = "username_unique", unique = true, columnList = "username")
})
public class RbacAuthUser extends UUIDIdAndTimeFieldEntity {
    @Column(length = 100, nullable = false, columnDefinition = "varchar(200) comment '用户名称，唯一'")
    private String username;
    @Column(length = 100, nullable = false, columnDefinition = "varchar(200) comment '用户密码'")
    private String password;
    @Column(length = 100, columnDefinition = "varchar(200) comment '用户昵称'")
    private String nickname;
    //序列号
    @Column(length = 9, nullable = false, columnDefinition = "int(9) comment '序列号'")
    private Integer serialNumber; //用户序列号，修改密码的时候递增，并且放在token中，用于校验用户登陆是否过期
}

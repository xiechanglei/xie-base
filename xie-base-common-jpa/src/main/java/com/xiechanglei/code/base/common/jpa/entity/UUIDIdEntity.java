package com.xiechanglei.code.base.common.jpa.entity;

import com.xiechanglei.code.base.common.jpa.generator.MyUUIDGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


/**
 * 使用UUID生成ID的实体基类，用在比较小的表上
 */
@Getter
@Setter
@MappedSuperclass
public class UUIDIdEntity implements BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",type = MyUUIDGenerator.class)
    @Column(length = 32, columnDefinition = "varchar(32) comment '物理主键'")
    private String id;
}

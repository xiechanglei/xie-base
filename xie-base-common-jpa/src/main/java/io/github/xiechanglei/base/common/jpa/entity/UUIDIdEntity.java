package io.github.xiechanglei.base.common.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * 使用UUID生成ID的实体基类，用在比较小的表上
 */
@Getter
@Setter
@MappedSuperclass
public class UUIDIdEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "io.github.xiechanglei.base.common.jpa.generator.MyUUIDGenerator")
    @Column(length = 32, columnDefinition = "varchar(32) comment '物理主键'")
    private String id;
}

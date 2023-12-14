package io.github.xiechanglei.base.common.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 使用雪花算法生成ID的实体基类，用在比较大的表上
 */
@Getter
@Setter
@MappedSuperclass
public abstract class SnowFlakeIdEntity implements BaseEntity{
    @Id
    @GenericGenerator(name = "snowFlakeIdGenerator", strategy = "io.github.xiechanglei.base.common.jpa.generator.SnowFlakeIdGenerator")
    @GeneratedValue(generator = "snowFlakeIdGenerator")
    @Column(length = 38, columnDefinition = "varchar(38) comment '物理主键'")
    public String id;
}
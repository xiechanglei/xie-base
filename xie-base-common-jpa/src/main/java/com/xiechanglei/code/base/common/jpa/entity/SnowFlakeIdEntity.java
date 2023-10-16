package com.xiechanglei.code.base.common.jpa.entity;

import com.xiechanglei.code.base.common.jpa.generator.SnowFlakeIdGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * 使用雪花算法生成ID的实体基类，用在比较大的表上
 */
@Getter
@Setter
@MappedSuperclass
public abstract class SnowFlakeIdEntity implements BaseEntity{
    @Id
    @GenericGenerator(name = "snowFlakeIdGenerator", type = SnowFlakeIdGenerator.class)
    @GeneratedValue(generator = "snowFlakeIdGenerator")
    @Column(length = 38, columnDefinition = "varchar(38) comment '物理主键'")
    public String id;
}
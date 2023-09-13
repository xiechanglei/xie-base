package com.xiechanglei.code.base.common.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 使用雪花算法生成ID的实体基类，用在比较大的表上
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class SnowFlakeIdAndTimeFieldEntity {
    @Id
    @GenericGenerator(name = "snowFlakeIdGenerator", strategy = "com.xiechanglei.code.base.common.jpa.generator.SnowFlakeIdGenerator")
    @GeneratedValue(generator = "snowFlakeIdGenerator")
    @Column(name = "id", length = 38, columnDefinition = "varchar(38) comment '物理主键'")
    public String id;

    /**
     * 更新时间
     */
    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "datetime comment '更新时间'")
    private Date updateTime;

    /**
     * 创建时间
     */
    @CreatedDate
    @Column(nullable = false, columnDefinition = "datetime comment '创建时间'")
    private Date createTime;
}
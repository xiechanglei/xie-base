package com.xiechanglei.code.base.common.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeFieldEntity {
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

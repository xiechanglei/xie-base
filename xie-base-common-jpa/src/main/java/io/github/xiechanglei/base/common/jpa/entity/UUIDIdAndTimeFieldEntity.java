package io.github.xiechanglei.base.common.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 使用UUID生成ID的实体基类，用在比较小的表上
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class UUIDIdAndTimeFieldEntity implements BaseEntity{
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "io.github.xiechanglei.base.common.jpa.generator.MyUUIDGenerator")
    @Column(length = 32, columnDefinition = "varchar(32) comment '物理主键'")
    private String id;

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

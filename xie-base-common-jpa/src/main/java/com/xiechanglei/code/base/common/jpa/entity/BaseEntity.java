package com.xiechanglei.code.base.common.jpa.entity;

import com.xiechanglei.code.base.common.jpa.annotation.NoOverwrite;
import com.xiechanglei.code.base.common.jpa.exception.ForkNotExistsException;
import com.xiechanglei.code.base.common.reflect.FieldHandler;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Id;
import java.lang.reflect.Field;
import java.util.Optional;

public interface BaseEntity {
    /**
     * 清除主键和创建时间以及修改时间的字段，使其变成一个干净的实体
     */
    default BaseEntity pure() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (FieldHandler.hasAnnotation(field, Id.class, LastModifiedDate.class, CreatedDate.class)) {
                field.setAccessible(true);
                try {
                    field.set(this, null);
                } catch (IllegalAccessException ignore) {
                }
            }
        }
        return this;
    }

    /**
     * 从数据库中获取旧的对象，并且fork到自己身上，可以完成更新的功能，并不保存，需要自行调用保存代码
     */
    default BaseEntity fork(JpaRepository<? extends BaseEntity, Object> repo) throws ForkNotExistsException {
        Object id = FieldHandler.getFiledValueByAnnotation(this, Id.class);
        if (id == null) {
            throw new ForkNotExistsException();
        }
        Optional<? extends BaseEntity> byId = repo.findById(id);
        byId.ifPresent(baseEntity -> {
            Field[] declaredFields = this.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (FieldHandler.hasAnnotation(declaredField, NoOverwrite.class, LastModifiedDate.class, CreatedDate.class)) {
                    declaredField.setAccessible(true);
                    try {
                        declaredField.set(this, declaredField.get(baseEntity));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        byId.orElseThrow(ForkNotExistsException::new);
        return this;
    }

}

package io.github.xiechanglei.base.common.reflect;

import java.lang.reflect.Field;

/**
 * FieldHelper 关于字段的工具类
 */
public class FieldHelper {
    /**
     * 获取字段的值
     */
    public static Object getValue(Object obj, String fieldName) {
        try {
            Field declaredField = obj.getClass().getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

}

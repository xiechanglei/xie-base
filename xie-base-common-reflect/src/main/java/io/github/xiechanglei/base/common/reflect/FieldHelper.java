package io.github.xiechanglei.base.common.reflect;

import java.lang.reflect.Field;

/**
 * FieldHelper 关于字段的工具类
 */
public class FieldHelper {
    /**
     * 判断字段是否有注解
     * @param field 字段
     * @param annotationClass 注解类
     * @return 是否有注解
     */
    public static boolean hasAnnotation(Field field, Class<?>... annotationClass) {
        for (Class aClass : annotationClass) {
            if (field.getAnnotation(aClass) != null) {
                return true;
            }
        }
        return false;
    }

}

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

    /**
     * 获取第一个拥有指定注解的字段的值
     * @param obj 对象
     * @param annotationClass 注解类
     * @return 字段值
     */
    public static <T> T getFiledValueByAnnotation(Object obj, Class annotationClass) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if ( declaredField.getAnnotation(annotationClass) != null) {
                declaredField.setAccessible(true);
                try {
                    return (T) declaredField.get(obj);
                } catch (IllegalAccessException ignore) {}
            }
        }
        return null;
    }
}

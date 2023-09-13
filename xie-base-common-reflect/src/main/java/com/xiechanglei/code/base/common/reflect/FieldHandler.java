package com.xiechanglei.code.base.common.reflect;

import java.lang.reflect.Field;

public class FieldHandler {
    public static boolean hasAnnotation(Field field, Class<?>... annotationClass) {
        for (Class aClass : annotationClass) {
            if (field.getAnnotation(aClass) != null) {
                return true;
            }
        }
        return false;
    }

    public static <T> Object getFiledValueByAnnotation(Object obj, Class annotationClass) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if ( declaredField.getAnnotation(annotationClass) != null) {
                declaredField.setAccessible(true);
                try {
                    return declaredField.get(obj);
                } catch (IllegalAccessException ignore) {}
            }
        }
        return null;
    }
}

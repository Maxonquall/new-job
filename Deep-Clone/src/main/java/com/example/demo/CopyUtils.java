package com.example.demo;

import java.lang.reflect.InvocationTargetException;

public class CopyUtils {
    public static Object deepCopy(Object obj) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        var clazz = obj.getClass();
        var fields = clazz.getDeclaredFields();
        var newObj = clazz.getDeclaredConstructor().newInstance();

        try {
            fields = clazz.getDeclaredFields();
            for (var field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                field.set(newObj, value);
            }
            return newObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}





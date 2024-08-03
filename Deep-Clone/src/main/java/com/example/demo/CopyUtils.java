package com.example.demo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CopyUtils {

    public static <T> T deepCopy(T obj) throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        if (obj == null) {
            return null;
        }

        var clazz = obj.getClass();

        if (clazz.isPrimitive() || clazz.equals(String.class)) {
            return obj;
        }

        if (Collection.class.isAssignableFrom(clazz)) {
            var originalCollection = (Collection<?>) obj;
            var newCollection = new ArrayList<>();
            for (var item : originalCollection) {
                newCollection.add(deepCopy(item));
            }
            return (T) newCollection;
        }

        if (Map.class.isAssignableFrom(clazz)) {
            var originalMap = (Map<?, ?>) obj;
            var newMap = new HashMap<>();
            for (var entry : originalMap.entrySet()) {
                newMap.put(deepCopy(entry.getKey()), deepCopy(entry.getValue()));
            }
            return (T) newMap;
        }

        var fields = clazz.getDeclaredFields();
        var newObj = (T) clazz.getDeclaredConstructor().newInstance();

        for (var field : fields) {
            field.setAccessible(true);
            var value = field.get(obj);

            if (value != null) {
                if (!field.getType().isPrimitive() && !field.getType().equals(String.class)) {
                    value = deepCopy(value);
                }
                field.set(newObj, value);
            }
        }

        return newObj;
    }
}

package com.example.demo;

import com.example.demo.models.Man;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        var man = new Man("Max", 29, List.of("Book1", "Book2"));

        var newMan = CopyUtils.deepCopy(man);

        writeClassAndFields(newMan);

    }

    private static void writeClassAndFields(Object obj) {
        Class<?> clazz = obj.getClass();
        System.out.println(clazz.getName());
        var fields = clazz.getDeclaredFields();

        for (var field : fields) {
            field.setAccessible(true);
            try {
                var fieldName = field.getName();
                var fieldValue = field.get(obj);
                System.out.print(fieldName + " : ");
                System.out.println(fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.example.demo;

import com.example.demo.models.AbstractModel;
import com.example.demo.models.Man;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {

        var man = new Man("Max", 29, List.of("Book1", "Book2"));

        var newMan = CopyUtils.deepCopy(man);

        writeClassAndFields(newMan);

        System.out.println(newMan.getName());
        var a = newMan.getAge() - 2;
        System.out.println(a); // 27
        newMan.setFavoriteBooks(List.of("Book3", "Book4"));
        writeClassAndFields(newMan);


        var map = new HashMap<String, String>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        var absModel = new AbstractModel("model1", map);
        var newAbsModel = CopyUtils.deepCopy(absModel);
        writeClassAndFields(newAbsModel);

    }

    private static void writeClassAndFields(Object obj) {
        Class<?> clazz = obj.getClass();
        String prefix = "com.example.demo.models.";
        System.out.println("Class is " + clazz.getName().substring(prefix.length()));
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

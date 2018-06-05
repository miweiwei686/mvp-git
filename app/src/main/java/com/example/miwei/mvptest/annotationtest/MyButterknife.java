package com.example.miwei.mvptest.annotationtest;


import java.lang.reflect.Field;
import java.util.TreeMap;

/**
 * 注解绑定类，仿照 butterknife
 */
public class MyButterknife {

    public static void bind(Student student) throws Exception {


        Class clazz = student.getClass();
        Field fieldName = clazz.getDeclaredField("name");
        Field fieldAge = clazz.getDeclaredField("age");
        fieldName.setAccessible(true);
        fieldAge.setAccessible(true);
        BindValue annotation = fieldName.getAnnotation(BindValue.class);
        if(annotation != null) {
            String name = annotation.name();
            int age = annotation.age();
            fieldName.set(student,name);
            fieldAge.set(student,age);
        } else {
            System.out.println("注解为空");
        }

    }

}

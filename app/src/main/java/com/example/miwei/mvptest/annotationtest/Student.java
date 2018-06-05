package com.example.miwei.mvptest.annotationtest;


/**
 * 注解的使用
 */
public class Student {

    @BindValue(name = "zhangsan",age = 10)
    private String name;
    private int age;
    public void study() {}

    @Override
    public String toString() {
        return "name : " +name+ ", age : " + age;
    }
}

package com.example.miwei.mvptest.annotationtest;

//@Target(ElementType.METHOD) 定义当前注解使用在方法上
//@Retention(RetentionPolicy.SOURCE) 表示当前注解存在于源码中，当源码被编译成字节码时，该注解被清除
//@Retention(RetentionPolicy.CLASS) 表示当前注解在字节码中，源码被编译成字节码时，该注解不会被清除

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface BindValue {
    int age();
    String name();
}

package com.example.miwei.mvptest.annotationtest;

public class TestAnnotation {

    public static void main() {
        Student student = new Student();
        try {
            MyButterknife.bind(student);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(student.toString());

    }
}

package com.example.cch.day06;

import java.lang.reflect.Field;

public class AccessPropertyTest {
    public static void main(String[] args) throws Exception{
        Class<?> stuClass = Class.forName("com.example.cch.day06.Student");
        Object o = stuClass.getDeclaredConstructor().newInstance();

        Field age = stuClass.getField("age");
        age.set(o, 16); // 操作屬性
        System.out.println(o);
        System.out.println(age.get(o)); // 需有 getAge 方法

        Field name = stuClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(o, "Naruto"); // name 為 static 因此物件可傳入 null
        System.out.println(o);
    }    
}

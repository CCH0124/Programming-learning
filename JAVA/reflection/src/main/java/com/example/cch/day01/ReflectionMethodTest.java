package com.example.cch.day01;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ReflectionMethodTest {
    public static void main(String[] args) throws Exception {
        Class cls = Class.forName("com.example.cch.day01.Cat");
        Object obj = cls.getDeclaredConstructor().newInstance();

        // 無法獲取 private 的屬性
        // Field name = cls.getField("name");
        // System.out.println(name.get(obj));

        Field age = cls.getField("age");
        System.out.println(age.get(obj));
        
        // 沒有參數表示該構造是無帶入參數
        Constructor constructor = cls.getConstructor();
        System.out.println(constructor);
        
        // 該構造器有一個 String 類型的參數
        
        Constructor constructor1 = cls.getConstructor(String.class);
        System.out.println(constructor1);
    }
}

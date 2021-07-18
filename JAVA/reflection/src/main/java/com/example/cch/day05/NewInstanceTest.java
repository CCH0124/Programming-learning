package com.example.cch.day05;

import java.lang.reflect.Constructor;

public class NewInstanceTest {
    public static void main(String[] args) throws Exception {
        Class<?> userClass = Class.forName("com.example.cch.day05.User");
        // No Arg Constructor
        Object o = userClass.getDeclaredConstructor().newInstance();
        System.out.println(o);
        // call have arg public constructor
        Constructor<?> constructor = userClass.getConstructor(String.class);
        o = constructor.newInstance("HSP");
        System.out.println(o);
        // call private constructor
        Constructor<?> constructor1 = userClass.getDeclaredConstructor(int.class, String.class);
        constructor1.setAccessible(true); // 使用反射訪問私有權限
        o = constructor1.newInstance(37, "naruto");
        System.out.println(o);
        
    }
}

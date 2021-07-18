package com.example.cch.day02;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.IntStream;

import com.example.cch.day01.Cat;

public class PerformenceTest {
    public static void main(String[] args) throws Exception {
        basic();
        reflection();
        reflectionImprove();
    }

    private static void basic() {
        Cat cat = new Cat();
        long s = System.currentTimeMillis();
        IntStream.range(0, 9000000).forEach( i -> cat.sleep());
        long e = System.currentTimeMillis();
        System.out.println("Basic Method Cost: "+ (e-s));
    }

    private static void reflection() throws Exception{
        Class cls = Class.forName("com.example.cch.day01.Cat");
        Object obj = cls.getDeclaredConstructor().newInstance();
        Method eat =  cls.getMethod("sleep"); // 獲取 Method 

        long s = System.currentTimeMillis();
        IntStream.range(0, 9000000).forEach( i -> {
            try {
                eat.invoke(obj); // 反射機制調用方法
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        long e = System.currentTimeMillis();
        System.out.println("Reflection Method Cost: "+ (e-s));
    }

    private static void reflectionImprove() throws Exception{
        Class cls = Class.forName("com.example.cch.day01.Cat");
        Object obj = cls.getDeclaredConstructor().newInstance();
        Method eat =  cls.getMethod("sleep"); // 獲取 Method 
        eat.setAccessible(true);
        long s = System.currentTimeMillis();
        IntStream.range(0, 9000000).forEach( i -> {
            try {
                eat.invoke(obj); // 反射機制調用方法
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        long e = System.currentTimeMillis();
        System.out.println("Reflection Improve Method Cost: "+ (e-s));
    }
}


// Basic Method Cost: 16
// Reflection Method Cost: 47
// Reflection Improve Method Cost: 39
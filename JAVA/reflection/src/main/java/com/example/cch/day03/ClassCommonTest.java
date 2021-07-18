package com.example.cch.day03;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ClassCommonTest {
    public static void main(String[] args) throws Exception{
        String carClassPath = "com.example.cch.day03.Car";
        // Get Car class
        Class<?> cls = Class.forName(carClassPath);
        // 顯示 cls 對象是哪個類的 Class 對象
        System.out.println("cls 的 Class 對象: " + cls);
        // 輸出 cls 運行類型
        System.out.println("cls 運行類型: " + cls.getClass());
        System.out.println("Get Package name: " + cls.getPackageName());
        System.out.println("Get full class name: " + cls.getName());
        // 建立一個 instance
        Car c = (Car)cls.getDeclaredConstructor().newInstance();
        System.out.println(c);
        // 獲取屬性
        Field brand = cls.getField("brand");
        System.out.println(brand.get(c));

        // 屬性賦值
        brand.set(c, "瑪莎拉蒂");
        System.out.println(brand.get(c));

        // 獲取所有屬性

        Field [] fields = cls.getFields();
        Arrays.stream(fields).forEach(f -> f.getName());
    }
}

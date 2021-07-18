package com.example.cch.day03;

public class GetClass {
    public static void main(String[] args) throws Exception {
        String carClassPath = "com.example.cch.day03.Car";
        // Type 1
        // 編譯階段
        Class<?> cls = Class.forName(carClassPath);

        // Type 2
        // 加載階段
        Class cls2 = Car.class;

        // Type 3
        // 運行階段

        Car c = new Car();

        Class cls3 = c.getClass();

        // Type 4
        // 類加載器方式
        // 每個物件都有類加載器
        ClassLoader classLoader = c.getClass().getClassLoader();

        Class cls4 = classLoader.loadClass(carClassPath);

    }
}

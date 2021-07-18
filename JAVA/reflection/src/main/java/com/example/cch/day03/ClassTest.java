package com.example.cch.day03;

import com.example.cch.day01.Cat;

public class ClassTest {
    public static void main(String[] args) throws Exception{

        // 傳統建立物件方式
        // Cat cat = new Cat();

        // 映射方式建立物件
        // 類加載過程只有一次，因此要將 Line 9 註解
        Class catClass = Class.forName("com.example.cch.day01.Cat");

        // 驗證類加載過程只有一次
        Class catClass2 = Class.forName("com.example.cch.day01.Cat");
        System.out.println(catClass.hashCode() == catClass2.hashCode());
    }
}

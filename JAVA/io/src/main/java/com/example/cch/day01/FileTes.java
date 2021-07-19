package com.example.cch.day01;

import java.io.File;
import java.io.IOException;

public class FileTes {
    public static void main(String[] args) throws IOException {
        createMethod1();
        createMethod2();
        createMethod3();
    }

    private static void createMethod1() throws IOException {
        String filePath = "C:\\Users\\ASUS\\Documents\\Programming-train\\JAVA\\io\\src\\main\\java\\com\\example\\cch\\resource\\createMethod1.txt";
        File file = new File(filePath);

        // 建立檔案，與硬碟交互
        file.createNewFile();
    }

    private static void createMethod2() throws IOException {
        File parentFile = new File("C:\\Users\\ASUS\\Documents\\Programming-train\\JAVA\\io\\src\\main\\java\\com\\example\\cch\\resource\\");
        String fileName = "createMethod2.txt";

        File file = new File(parentFile, fileName);
        // 建立檔案，與硬碟交互
        file.createNewFile();
    }

    private static void createMethod3() throws IOException {
        String parent = "C:\\Users\\ASUS\\Documents\\Programming-train\\JAVA\\io\\src\\main\\java\\com\\example\\cch\\resource\\";
        String fileName = "createMethod3.txt";
        File file = new File(parent, fileName);
        file.createNewFile();

    }
}

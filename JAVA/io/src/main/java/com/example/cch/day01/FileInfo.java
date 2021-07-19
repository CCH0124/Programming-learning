package com.example.cch.day01;

import java.io.File;

public class FileInfo {
    public static void main(String[] args) {
        File file = new File("src/main/java/com/example/cch/resource/createMethod1.txt");
        System.out.println("File name: " + file.getName());
        System.out.println("Absolute Path: " + file.getAbsolutePath());
        System.out.println("Get Parent: " + file.getParent());
        System.out.println("Exist: " + file.exists());
        System.out.println("Is File: " + file.isFile());
        System.out.println("Is Dir: " + file.isDirectory());
        System.out.println("File size: " + file.length());
    }
}

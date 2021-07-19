package com.example.cch.day02;

import java.io.FileInputStream;
import java.io.IOException;

public class FileInputStreamTest {
    public static void main(String[] args) throws IOException {
        // readFileMethod1();
        readFileMethod2();
    }

    public static void readFileMethod1() throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/hello.txt";
        int read = 0;
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
           while ((read = fileInputStream.read()) != -1) {
                System.out.print((char)read);
           }
        } 
    }
    public static void readFileMethod2() throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/hello.txt";
        int readLen = 0;
        byte[] buff = new byte[8];
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
           while ((readLen = fileInputStream.read(buff)) != -1) {
                System.out.print(new String(buff, 0, readLen));
           }
        } 
    }
}

package com.example.cch.day02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyImage {
    public static void main(String[] args) {
        String srcFilePath = "src/main/java/com/example/cch/resource/6422.png";
        String dstFilePath = "src/main/java/com/example/cch/resource/image/6422.png";

        byte [] buff = new byte[1024];
        int readLine = 0;
        try (FileInputStream fileInputStream = new FileInputStream(srcFilePath);
                FileOutputStream fileOutputStream = new FileOutputStream(dstFilePath)) {
                while((readLine = fileInputStream.read(buff)) != -1) {

                    fileOutputStream.write(buff, 0, readLine); // 避免檔案損失
                }
                System.out.println("Copy success~");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

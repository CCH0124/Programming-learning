package com.example.cch.day05;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedStreamCopyImage {
    public static void main(String[] args) {
        String srcFilePath = "src/main/java/com/example/cch/resource/6422.png";
        String dstFilePath = "src/main/java/com/example/cch/resource/image/BufferedStreamCopyImage_6422.png";

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(srcFilePath));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dstFilePath))) {
                byte [] buff = new byte[1024];
                int readLine = 0;

                while((readLine = bufferedInputStream.read(buff)) != -1 ){
                    bufferedOutputStream.write(buff, 0, readLine);
                }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.example.cch.day05;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class BufferedStreamCopyMusic {
    public static void main(String[] args) {
        String srcFilePath = "src/main/java/com/example/cch/resource/Lucid Dreamer.mp3";
        String dstFilePath = "src/main/java/com/example/cch/resource/music/BufferedStreamCopyMusic_Lucid Dreamer.mp3";

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

        File src = new File(srcFilePath);
        File dst = new File(dstFilePath);
        System.out.println(src.length() == dst.length());
    }
}

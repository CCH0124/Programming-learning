package com.example.cch.day03;

import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest {
    public static void main(String[] args) {
        readerMethod2();
    }

    private static void readerMethod1() {
        String filePath = "src/main/java/com/example/cch/resource/withoutYou.txt";
        int data = 0;
        try (FileReader fileReader = new FileReader(filePath)) {
            // 單個字符讀取
            while ((data = fileReader.read()) != -1) {
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readerMethod2() {
        String filePath = "src/main/java/com/example/cch/resource/withoutYou.txt";
        int readLine = 0;
        char[] buff = new char[8];
        try (FileReader fileReader = new FileReader(filePath)) {
            // 單個字符讀取
            while ((readLine = fileReader.read(buff)) != -1) {
                System.out.print(new String(buff, 0, readLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

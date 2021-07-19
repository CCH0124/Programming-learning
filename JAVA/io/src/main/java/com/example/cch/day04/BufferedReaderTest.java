package com.example.cch.day04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/withoutYou.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

        String line; // 按行讀取
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        bufferedReader.close();
    }
}

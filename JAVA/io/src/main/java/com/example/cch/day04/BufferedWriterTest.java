package com.example.cch.day04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedWriterTest {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/BufferedWriterTest.txt";

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true));
        bufferedWriter.write("Hello");
        bufferedWriter.newLine(); // 換行
        bufferedWriter.write(123); // 轉 ascii
        bufferedWriter.write("World", 0, 3);

        bufferedWriter.close();
    }
}

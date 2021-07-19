package com.example.cch.day03;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterTest {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/example/cch/resource/FileWriter.txt";
        char [] chars = {'a', 'b', 'c'};
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write('A');
            fileWriter.write(chars);
            fileWriter.write("Hello World!".toCharArray(), 0, 5);
            fileWriter.write("str");
            fileWriter.write("str", 0, 2);
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}

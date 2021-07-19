package com.example.cch.day02;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamTest {
    public static void main(String[] args) throws IOException {
        writeFile();
    }

    private static void writeFile() throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/FileOutputStreamTest.txt";

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            fileOutputStream.write('a');
            String s = "Hello World~~";
            fileOutputStream.write(s.getBytes());
            fileOutputStream.write(s.getBytes(), 0, 4);
        }
    }
}

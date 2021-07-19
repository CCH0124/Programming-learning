package com.example.cch.day08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutpuStreamTest {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/OutpuStreamTest.txt";
        String charSet = "utf-8";

        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), charSet);
        osw.write("Hello, 你好");
        osw.close();

    }
}

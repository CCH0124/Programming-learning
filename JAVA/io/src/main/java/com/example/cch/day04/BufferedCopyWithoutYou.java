package com.example.cch.day04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedCopyWithoutYou {
    public static void main(String[] args) {
        String srcFilePath = "src/main/java/com/example/cch/resource/withoutYou.txt";
        String dstFilePath = "src/main/java/com/example/cch/resource/withoutYou_use_buffered_copy.txt";

        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(srcFilePath)); 
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dstFilePath))) {
                    while ((line = bufferedReader.readLine()) != null) {
                        bufferedWriter.write(line);
                        bufferedWriter.newLine();
                    }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

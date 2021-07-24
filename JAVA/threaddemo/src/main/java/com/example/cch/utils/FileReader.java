package com.example.cch.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.FileReader")
public class FileReader {
    public static void read(String filePath) {
        File file = new File(filePath);
        try (FileInputStream in = new FileInputStream(file)) {
            long start = System.currentTimeMillis();
            log.info("read {} start...", file.getName());

            byte[] buf = new byte[1024];
            int n = -1;
            do {
                n = in.read(buf);
            } while (n != -1);

            long end = System.currentTimeMillis();

            log.info("Read End... Cost: {}", end-start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}

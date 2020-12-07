package com.lab.cch.io;

import org.junit.Test;

import java.io.*;

public class FileIOStream {
    @Test
    public void testFileInputStream() {
        File file = new File("hello.txt");
        FileInputStream fis = null;
        byte[] buffer = new byte[5];
        int len;
        try {
            fis = new FileInputStream(file);
            while ((len = fis.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void readPhoto() {
        FileInputStream fis = null;
        FileOutputStream fds = null;
        try {
            File src = new File("photo-5e5621af2bca9.jpg");
            File des = new File("photo-5e5621af2bca9_copy.jpg");
            fis = new FileInputStream(src);
            fds = new FileOutputStream(des);
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fds.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fds.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




    }

}

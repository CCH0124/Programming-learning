package com.lab.cch.io;

import org.junit.Test;

import java.io.*;

public class bufferTest {
    @Test
    public void BufferedStreamTest(){
        FileInputStream fis = null;
        FileOutputStream fds = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            File src = new File("photo-5e5621af2bca9.jpg");
            File des = new File("photo-5e5621af2bca9_copy_buffer.jpg");
            fis = new FileInputStream(src);
            fds = new FileOutputStream(des);

            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fds);


            byte[] buffer = new byte[5];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 當 buffer 來封裝 Stream 的方法時，關閉 Buffer 資源時，Stream 資源也會被關閉
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @Test
    public void testBufferedReaderWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(new File("bufferedReaderWrite.txt")));
            bw = new BufferedWriter(new FileWriter(new File("bufferedReaderWrite1.txt")));
            char[] cbuf = new char[1024];
            int len;
            // method1
            while ((len = br.read(cbuf)) != -1){
                bw.write(cbuf, 0, len);
            }
            // method2
            String data;
            while ((data = br.readLine()) != null) {
                bw.write(data); // 不包含換行
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





    }
}

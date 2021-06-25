package com.example.day02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestChannelFileCopy {
    /**
     * 使用 channel 完成檔案複製
     * 
     * @param args
     */
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("src\\main\\java\\com\\example\\day02\\0219.jpg");
                FileOutputStream fos = new FileOutputStream("src\\main\\java\\com\\example\\day02\\0219-copy.jpg");
                // 獲取 channel
                FileChannel inChannel = fis.getChannel();
                FileChannel outChannel = fos.getChannel()) {
            // 分配 Buffer 大小
            ByteBuffer buf = ByteBuffer.allocate(1024);

            // channel 數據存入 Buffer
            while (inChannel.read(buf) != -1) {
                buf.flip(); // read 模式
                outChannel.write(buf);
                buf.clear();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}

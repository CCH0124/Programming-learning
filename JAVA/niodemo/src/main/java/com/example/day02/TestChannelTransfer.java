package com.example.day02;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestChannelTransfer {
    public static void main(String[] args) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("src\\main\\java\\com\\example\\day02\\0219.jpg"), StandardOpenOption.READ);
        // StandardOpenOption.CREATE_NEW 不存在建立，否則異常
        FileChannel outChannel = FileChannel.open(Paths.get("src\\main\\java\\com\\example\\day02\\0219-Copy-transfer.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);
    
        inChannel.transferTo(0, inChannel.size(), outChannel);
        // outChannel.transferFrom(inChannel, 0, inChannel.size());
        
        inChannel.close();
        outChannel.close();
    }
}

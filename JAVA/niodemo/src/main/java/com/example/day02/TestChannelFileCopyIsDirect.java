package com.example.day02;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestChannelFileCopyIsDirect {
    public static void main(String[] args) throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("src\\main\\java\\com\\example\\day02\\0219.jpg"), StandardOpenOption.READ);
        // StandardOpenOption.CREATE_NEW 不存在建立，否則異常
        FileChannel outChannel = FileChannel.open(Paths.get("src\\main\\java\\com\\example\\day02\\0219-Copy-Is-Direct.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);
        // 記憶體映射檔案
        MappedByteBuffer inMappedByteBuffer = inChannel.map(MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedByteBuffer =outChannel.map(MapMode.READ_WRITE, 0, inChannel.size()); // 權限需要與 FileChannel 對應

        // 直接對 Buffer 數據讀寫
        byte[] dst = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(dst);
        outMappedByteBuffer.put(dst);

        inChannel.close();
        outChannel.close();
    }
}

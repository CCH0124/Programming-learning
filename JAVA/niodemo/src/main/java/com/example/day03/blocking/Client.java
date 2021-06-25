package com.example.day03.blocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel inChannel = FileChannel.open(Paths.get("src\\main\\java\\com\\example\\day02\\0219.jpg"), StandardOpenOption.READ);
        
        ByteBuffer buf = ByteBuffer.allocate(1024);

        while(inChannel.read(buf) != -1){
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}

        sChannel.shutdownOutput();
        
        int len = 0;
        while((len = sChannel.read(buf)) != -1) {
            buf.flip();
			System.out.println(new String(buf.array(), 0, len));
			buf.clear();
        }
        inChannel.close();
        sChannel.close();
    }
}

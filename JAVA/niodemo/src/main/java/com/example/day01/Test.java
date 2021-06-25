package com.example.day01;

import java.nio.ByteBuffer;

public class Test {
    public static void main(String[] args) {
        // 指定大小的緩衝區
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        System.out.println("----------------------------------allocate----------------------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("----------------------------------put data----------------------------");

        String s = "I am itachi";

        byteBuffer.put(s.getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("----------------------------------read data mode----------------------------");

        byteBuffer.flip(); // 讀取模式

        byteBuffer.put(s.getBytes());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("----------------------------------read data in buffer----------------------------");

        byte[] dst = new byte[byteBuffer.limit()];
        byteBuffer.get(dst);

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("----------------------------------repeat read data in buffer----------------------------");
        byteBuffer.rewind(); // 可重複讀

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("----------------------------------clear buffer----------------------------");
        byteBuffer.clear(); // 數據不會被清空

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println("----------------------------------remark----------------------------");
        s = "abc";
        ByteBuffer bb = ByteBuffer.allocate(1024);

        bb.put(s.getBytes());
        bb.flip();

        dst = new byte[bb.limit()];

        bb.get(dst, 0, 2); // 讀取從第 0 個位置的數據，讀取長度為 2
        System.out.println(new String(dst, 0, 2));
        System.out.println(bb.position());

        bb.mark(); // 標記

        bb.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(bb.position());

        bb.reset(); // 恢復到 mark 的位置
        System.out.println(bb.position());

        if (bb.hasRemaining()) { // 是否在 Buffer 中還有剩餘數據
            System.out.println(bb.remaining()); // 可操作的數量
        }
    }
}

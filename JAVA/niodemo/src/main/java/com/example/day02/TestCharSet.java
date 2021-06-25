package com.example.day02;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class TestCharSet {
    public static void main(String[] args) throws CharacterCodingException {
        Charset cs1 = Charset.forName("GBK");
		
		//獲取編碼器
		CharsetEncoder ce = cs1.newEncoder();
		
		//獲取解碼器
		CharsetDecoder cd = cs1.newDecoder();
		
		CharBuffer cBuf = CharBuffer.allocate(1024);
        
		cBuf.put("歡迎光臨！");
		cBuf.flip();
		
		//編碼
		ByteBuffer bBuf = ce.encode(cBuf);
		
		for (int i = 0; i < bBuf.limit(); i++) {
			System.out.println(bBuf.get());
		}
		
		//解碼
		bBuf.flip();
		CharBuffer cBuf2 = cd.decode(bBuf);
		System.out.println(cBuf2.toString());
		
		System.out.println("------------------------------------------------------");
		
		Charset cs2 = Charset.forName("UTF-8");
		bBuf.flip();
		CharBuffer cBuf3 = cs2.decode(bBuf);
		System.out.println(cBuf3.toString());
    }
}

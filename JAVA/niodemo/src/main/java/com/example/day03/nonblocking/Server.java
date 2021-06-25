package com.example.day03.nonblocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
    public static void main(String[] args) throws IOException {
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		
		ssChannel.configureBlocking(false);
		
		ssChannel.bind(new InetSocketAddress(9898));
		
		Selector selector = Selector.open();
		
		//5. 註冊 channel 到 selector 上, 並指定`監聽接收事件`
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		//6. 輪詢式的獲取選擇器上已準備就緒的事件
		while(selector.select() > 0){

			//7. 獲取當前選擇器中所有註冊的“選擇鍵(已就緒的監聽事件)”
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();
			
			while(it.hasNext()){
				//8. 獲取準備“就緒”的是事件
				SelectionKey sk = it.next();
				
				//9. 判斷具體是什麼事件準備就緒
				if(sk.isAcceptable()){
					//10. 若“接收就緒”，獲取客戶端連接
					SocketChannel sChannel = ssChannel.accept();
					
					sChannel.configureBlocking(false);
					
					//12. 將該通道註冊到選擇器上
					sChannel.register(selector, SelectionKey.OP_READ);
				}else if(sk.isReadable()){
					//13. 獲取當前選擇器上“讀就緒”狀態的通道
					SocketChannel sChannel = (SocketChannel) sk.channel();
					
					//14. 讀取數據
					ByteBuffer buf = ByteBuffer.allocate(1024);
					
					int len = 0;
					while((len = sChannel.read(buf)) > 0 ){
						buf.flip();
						System.out.println(new String(buf.array(), 0, len));
						buf.clear();
					}
				}
				
				//15. 取消選擇鍵 SelectionKey
				it.remove();
			}
		}
    }
}

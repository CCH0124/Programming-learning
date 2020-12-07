package com.lab.cch.network;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPTest {
    @Test
    public void client(){
        InetAddress inet = null;
        Socket socket = null;
        OutputStream os = null;
        try {
            inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);
            os = socket.getOutputStream();
            os.write("I like you".getBytes());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }





    }
    @Test
    public void server(){
        ServerSocket serverSocket = null;
        Socket accept = null;
        InputStream acceptInputStream = null;
        ByteArrayOutputStream baos = null;
        try {
            serverSocket = new ServerSocket(8899);
            accept = serverSocket.accept();
            acceptInputStream = accept.getInputStream();
            baos = new ByteArrayOutputStream(); // 蒐集完一次轉換
            byte[] buffer = new byte[5];
            int len;
            while((len = acceptInputStream.read(buffer)) != -1){
                baos.write(buffer,0, len);
            }
            System.out.println("From: "+ accept.getInetAddress().getHostAddress());
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                acceptInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                accept.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

package com.lab.cch.network;

import org.junit.Test;

import java.io.IOException;
import java.net.*;

public class UDPTest {
    @Test
    public void sender(){
        DatagramSocket datagramSocket = null;
        DatagramPacket datagramPacket = null;
        InetAddress inet = null;
        try{
            datagramSocket = new DatagramSocket();
            String str = "UDP flow";
            byte[] data = str.getBytes();
            inet = InetAddress.getLocalHost();
            datagramPacket = new DatagramPacket(data,0,data.length, inet, 9999);

            datagramSocket.send(datagramPacket);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            datagramSocket.close();
        }
    }

    @Test
    public void reveiver(){
        DatagramSocket datagramSocket = null;
        DatagramPacket datagramPacket = null;
        try{
            datagramSocket = new DatagramSocket(9999);
            byte[] buff = new byte[64];
            datagramPacket = new DatagramPacket(buff,0,buff.length);
            datagramSocket.receive(datagramPacket);
            System.out.println(new String(datagramPacket.getData(), 0, datagramPacket.getLength()));
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            datagramSocket.close();
        }
    }
}

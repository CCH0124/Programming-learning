package com.lab.cch.network;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    public static void main(String[] args) {

        try {
            InetAddress inet  = InetAddress.getByName("192.168.100.12");
            System.out.println(inet);
            InetAddress inet2 = InetAddress.getByName("google.com.tw");
            System.out.println(inet2);
            InetAddress inet3 = InetAddress.getLocalHost();
            System.out.println(inet3);

            System.out.println(inet2.getHostName());
            System.out.println(inet2.getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }


}

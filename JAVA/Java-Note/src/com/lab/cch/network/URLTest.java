package com.lab.cch.network;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class URLTest {
    @Test
    public void getURLInfo() {
        try {
            URL url = new URL("https://hackmd.io/@njgpAbPmRqOJa0cV1qd2Mw/S1V4HngYX/%2Fc%2Ftutorials-tw?type=book");
            System.out.println(url.getProtocol());
            System.out.println(url.getHost());
            System.out.println(url.getPort());
            System.out.println(url.getQuery());
            System.out.println(url.getPath());
            System.out.println(url.getFile());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}

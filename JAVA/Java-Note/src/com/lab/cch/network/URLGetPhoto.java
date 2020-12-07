package com.lab.cch.network;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLGetPhoto {
    public static void downloadPhoto(String path, String filename){
        URL url = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            url = new URL(path);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            fileOutputStream = new FileOutputStream("src/com/company/cch/network/"+filename);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1){
                fileOutputStream.write(buffer,0,len);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.disconnect();
        }
    }
    public static void main(String[] args) throws IOException {
//        https://pbs.twimg.com/media/EUfufWrUUAAUUmq?format=jpg&name=medium
        downloadPhoto("https://pbs.twimg.com/media/EUfufWrUUAAUUmq?format=jpg&name=medium", "yuri.jpg");
    }
}

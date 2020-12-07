package com.lab.cch.io.exer;

import java.io.*;

public class PhotoEncry {
    public static void encry(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File("photo-5e5621af2bca9_copy.jpg"));
            fos = new FileOutputStream(new File("photo_encry.jpg"));
            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1){
                for (int i=0; i<len; i++){
                    buffer[i] = (byte)(buffer[i]^5);
                }

                fos.write(buffer,0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void decry(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(new File("photo_encry.jpg"));
            fos = new FileOutputStream(new File("photo_encry_decry.jpg"));
            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1){
                for (int i=0; i<len; i++){
                    buffer[i] = (byte)(buffer[i]^5);
                }

                fos.write(buffer,0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        encry();
        decry();
    }
}

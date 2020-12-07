package com.lab.cch.io;

import java.io.*;

public class FileReaderTest {
    public static void testFileReader() {
        FileReader fr = null;
        try {
            File file = new File("hello.txt");
            fr = new FileReader(file);
            int data = fr.read(); // 檔案到結尾時傳 -1
            while (data != -1) {
                System.out.print((char)data);
                data = fr.read();
            }
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testFileReader1() {
        File file = new File("hello.txt");
        FileReader fr = null;
        char[] cbuf = new char[5];
        int len;
        try {
            fr = new FileReader(file);
            while ((len = fr.read(cbuf)) != -1) {
                /**
                 * [h,e,l,l,o]
                 * ['',w,o,r,l]
                 * [d,1,2,3,l] # l 未被覆蓋
                 * for(int i=0; i<cbuf.length; i++){
                 *  System.out.print(cbuf[i]);
                 * }
                 */
                // method 1
                for(int i=0; i<len; i++){
                    System.out.print(cbuf[i]);
                }
                System.out.println();
                // method 2
                String str = new String(cbuf, 0 , len);
                System.out.print(str);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void testFileWriter(){
        File file = new File("fileWriter.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write("I have a dream!");
            fw.write("You need to have a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readPhoto() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            File src = new File("photo-5e5621af2bca9.jpg");
            File des = new File("photo-5e5621af2bca9_copy.jpg");
            /**
             * 無法使用字符流以下寫圖檔
             */
            fr = new FileReader(src);
            fw = new FileWriter(des);

            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1) {
                fw.write(cbuf, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        testFileWriter();
    }
}

package com.lab.cch.io;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileTest {

    public static void test1(){
        /**
         * 相對路徑 相較於某個路徑下，指名路徑
         * 絕對路徑 包含硬碟在內的檔案或目錄的路徑
         */
        File file = new File("hello.txt");
        File file2 = new File("C:\\Users\\user\\Desktop\\java\\src\\com\\company\\cch\\io\\file2.txt");
        File file3 = new File("C:\\Users\\user\\Desktop", "java");
        File file4 = new File(file3, "f4.txt");
        System.out.println(file);
        System.out.println(file2);
        System.out.println(file3);
        System.out.println(file4);
    }
    public static void test2() {
        File file = new File("hello.txt");
        File file2 = new File("D:\\javaio\\hi.txt");

        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(new Date(file.lastModified()));
        System.out.println("----------------------------------------------------------------------");
        System.out.println(file2.getAbsolutePath());
        System.out.println(file2.getPath());
        System.out.println(file2.getName());
        System.out.println(file2.getParent());
        System.out.println(file2.length());
        System.out.println(file2.lastModified());
    }

    public static void test3() {
        File file = new File("C:\\Users\\user\\Desktop\\java");
        String[] list = file.list();
        for (String s: list) {
            System.out.println(s);
        }
        System.out.println("----------------------------------------------------------------------");
        File[] files = file.listFiles();
        for (File f: files ) {
            System.out.println(f);
        }
    }

    public static void test4(){
        File file = new File("hello.txt"); // must exist
        File file2 = new File("D:\\javaio\\hi.txt"); // hi.txt cannot exist
        System.out.println(file.renameTo(file2));
    }

    public static void test5() throws IOException {
        File file1 = new File("hi.txt");
        if(!file1.exists()) {
            file1.createNewFile();
            System.out.println("Succ");
        } else {
            file1.delete();
            System.out.println("Del");
        }
    }

    public static void test6() throws IOException {
        File file1 = new File("D:\\java_io");
        boolean mkdir = file1.mkdir();
        if(mkdir){
            System.out.println("Succ");
        }

        File file2 = new File("D:\\java_io\\io\\io1");
        boolean mkdirs = file2.mkdirs();
        if(mkdirs){
            System.out.println("Succ mkdirs");
        }
    }
    public static void main(String[] args) throws IOException {
        test6();
    }
}

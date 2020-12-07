package com.lab.cch.reflection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClassLoaderTest {
    @Test
    public void test() throws IOException {
        Properties pro = new Properties();
        // 讀取配置檔案方式一
//        FileInputStream fis = new FileInputStream("src/jdbc.properties");
//        pro.load(fis);
        // 使用 ClassLoader
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("src/jdbc1.properties");
        pro.load(is);

        String user = pro.getProperty("user");
        String pwd = pro.getProperty("passwd");
        System.out.println(user);
        System.out.println(pwd);
    }
}

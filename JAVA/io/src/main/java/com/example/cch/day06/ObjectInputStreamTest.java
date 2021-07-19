package com.example.cch.day06;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filePath = "src/main/java/com/example/cch/resource/data.dat";

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readUTF());
        // 編譯時是 Object 類型，運行時是 Dog 類型
        Object dog = ois.readObject();
        System.out.println(dog.getClass());
        System.out.println(dog);

        Dog dog2 = (Dog)dog;
        System.out.println(dog2.getName());
        ois.close();
    }
}

package com.example.cch.day06;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectOutputStreamTest {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/com/example/cch/resource/data.dat";

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
        objectOutputStream.writeInt(100);
        objectOutputStream.writeBoolean(true);
        objectOutputStream.writeChar('a');
        objectOutputStream.writeUTF("火影");
        objectOutputStream.writeObject(new Dog("柴柴", 2));

        objectOutputStream.close();

        
    }
}

// Serializable 表示該類可序列化
class Dog implements Serializable {
    private String name;
    private int age;

    public Dog() {

    }


    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }


}

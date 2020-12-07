package com.lab.cch.io;

import org.junit.Test;

import java.io.*;

public class ObjectStream {
    /**
     * 序列化
     */
    @Test
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File("object.dat")));
            oos.writeObject(new String("I like Taiwan"));
            oos.flush();
            oos.writeObject(new Person("Itachi", 22));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 反序列化
     */
    @Test
    public void testObjectIntputStream(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(new File("object.dat")));
            Object obj = ois.readObject();
            String str = (String)obj;
            Person p = (Person)ois.readObject();
            System.out.println(str);
            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

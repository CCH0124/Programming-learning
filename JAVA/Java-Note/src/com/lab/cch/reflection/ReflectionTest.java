package com.lab.cch.reflection;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
    @Test
    public void reflectionTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        /**
         * 反射建立物件
         */
        Class cl = Person.class;
        Constructor constructor = cl.getConstructor(String.class, int.class);
        Object instance = constructor.newInstance("Tom", 12);
        Person p = (Person)instance;
        System.out.println(p.toString());
        /**
         * 調用屬性
         */
        Field age = cl.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p.toString());
        /**
         * 調用方法
         */
        Method show = cl.getDeclaredMethod("show");
        show.invoke(p);
        /**
         * 調用私有結構
         */
        Constructor declaredConstructor = cl.getDeclaredConstructor((String.class));
        declaredConstructor.setAccessible(true);
        Person p1 = (Person) declaredConstructor.newInstance("Jerry");
        System.out.println(p1);

        Field name = cl.getDeclaredField("name");
        name.setAccessible(true);
        name.set(p1, "Itachi");
        System.out.println(p1);

        Method showNation = cl.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String)showNation.invoke(p1, "Taiwan");
        System.out.println(nation);
    }

    @Test
    public void test3() throws ClassNotFoundException {
        // 調用運行時類的屬性
        Class cl = Person.class;
        System.out.println(cl);
        Person p1 = new Person();
        Class cl2 = p1.getClass();
        System.out.println(cl2);

        Class cl3 = Class.forName("com.company.cch.reflection.Person");
        System.out.println(cl3);

        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class cl4 = classLoader.loadClass("com.company.cch.reflection.Person");
        System.out.println(cl4);
    }
}

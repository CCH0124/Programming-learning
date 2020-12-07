package com.lab.cch.reflection;

import org.junit.Test;


public class newInstanceTest {
    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class cl = Person.class;
        Person p1 = (Person) cl.newInstance();
        System.out.println(p1);



    }
}

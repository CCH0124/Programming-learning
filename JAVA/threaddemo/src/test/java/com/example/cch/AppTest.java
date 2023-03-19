package com.example.cch;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    
    class Person {
        int age;
        String name;

        @Override
        public String toString() {
            // TODO Auto-generated method stub
            return super.toString();
        }
    }
    public void modify(int i, String s, Person p) {
        i = i * 2;
        s = "Goodbye";
        p.name = "Bob";
        
    }
    @Test
    public void modifyTest() {
        Person p = new Person();
        int i = 2;
        String s = "Hello";
        modify(i, s, p);
        System.out.println(i);
        System.out.println(s);
        System.out.println(p.age);
        System.out.println(p.name);

    }
}

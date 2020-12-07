package com.lab.cch.reflection.useother;

import com.lab.cch.reflection.other.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class MethodTest {
    @Test
    public void test1(){
        Class cl = Person.class;
        // 獲取當前運行時類和其父類中 public 權限的方法
        Method[] methods = cl.getMethods();
        for (Method m: methods) {
            System.out.println(m);
        }
        // 獲取當前運行時類中所有方法
        Method[] methods1 = cl.getDeclaredMethods();
        for (Method m: methods1) {
            System.out.println(m);
        }
    }

    @Test
    public void test2(){
        Class cl = Person.class;
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods) {
            Annotation [] annos = m.getAnnotations();
            for (Annotation a: annos) {
                System.out.println(a);
            }
            System.out.print(Modifier.toString(m.getModifiers()) + '\t');
            System.out.print(m.getReturnType().getName() + '\t');
            System.out.print(m.getName() + '\t');

        }
    }
}

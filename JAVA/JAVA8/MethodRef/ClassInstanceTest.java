package MethodRef;

import java.util.function.BiPredicate;

import org.junit.Test;

public class ClassInstanceTest {
    @Test
    public void test(){
        BiPredicate<String, String> bp = String::equals; // 原本可以 (x,y) -> x.equals(y) 表達
    }
}

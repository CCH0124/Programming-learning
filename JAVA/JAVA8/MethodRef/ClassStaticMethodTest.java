package MethodRef;

import java.util.Comparator;

import org.junit.Test;

public class ClassStaticMethodTest {
    @Test
    public void test() {
        Comparator<Integer> com = Integer::compare;
    }
}

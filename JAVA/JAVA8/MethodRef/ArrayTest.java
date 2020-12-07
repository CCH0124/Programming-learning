package MethodRef;

import java.util.function.Function;

import org.junit.Test;

public class ArrayTest {
    @Test
    public void test() {
        Function<Integer, String[]> fun = String[]::new;
    }
}

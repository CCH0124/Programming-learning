package javaexistfunctionInterface;

import java.util.function.Function;

import org.junit.Test;

public class FunctionTest {
    @Test
    public void test1(){
       String res = strHandler("Hello Itachi Naruto   ", (str) -> str.trim());
       System.out.println(res);
    }
    public String strHandler(String str, Function<String,String> fun){
        return fun.apply(str);
    }
}
